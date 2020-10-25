package org.systers.mentorship.remote

import android.content.Intent
import androidx.annotation.NonNull
import android.text.TextUtils
import androidx.core.content.ContextCompat
import okhttp3.Interceptor
import okhttp3.Response
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.utils.getAuthTokenPayload
import org.systers.mentorship.view.activities.LoginActivity

/**
 * Represents a custom HTTP requests interceptor
 */
class CustomInterceptor : Interceptor {

    var preferenceManager: PreferenceManager = PreferenceManager()

    override fun intercept(@NonNull chain: Interceptor.Chain): Response? {

        val chainRequest = chain.request()
        val urlPath = chain.request().url().encodedPath()
        val builder = chainRequest.newBuilder()
        val accessToken = preferenceManager.authToken

        if (urlPath == "/login" || urlPath == "/register" || urlPath == "/refresh") {
            val request = builder.build()
            return chain.proceed(request)
        }

        val accessTokenExpTime = getAuthTokenPayload().exp
        val refreshToken = preferenceManager.refToken
        //To Check is RefreshToken is Empty
        if (TextUtils.isEmpty(refreshToken)) {
            //If refresh token is empty -> Navigate to login screen
            preferenceManager.clear()
            val intent = Intent(MentorshipApplication.getContext(), LoginActivity::class.java)
            intent.putExtra(Constants.TOKEN_EXPIRED_EXTRA, 0)
            ContextCompat.startActivity(MentorshipApplication.getContext(), intent, null)
            return null
        }

        //To Check if the AuthToken has Expired and request is not refresh or login
        if (accessTokenExpTime < System.currentTimeMillis() / 1000 && chainRequest.url().encodedPath() != "/refresh") {
            val responseToken = AuthDataManager().refresh(refreshToken).execute()

            return when {
                (responseToken == null || responseToken.code() != 200) -> {
                    // Navigate Back to Login if request is null or response is not 200
                    preferenceManager.clearAuthToken()
                    val intent = Intent(MentorshipApplication.getContext(), LoginActivity::class.java)
                    intent.putExtra(Constants.TOKEN_EXPIRED_EXTRA, 0)
                    ContextCompat.startActivity(MentorshipApplication.getContext(), intent, null)
                    null
                }
                else -> {
                    //Put new authToken is prefs
                    responseToken.body()?.accessToken.let {
                        if (it != null) {
                            preferenceManager.putAuthToken(it)
                        }
                    }
                    //Make request with new Auth token
                    val newRequest = chainRequest.newBuilder().addHeader("Authorization", preferenceManager.authToken).build()
                    chain.proceed(newRequest)
                }
            }

        }
        //Token valid make request
        else {
            if (!TextUtils.isEmpty(accessToken) ) {
                builder.header("Authorization", preferenceManager.authToken)
            }

            val request = builder.build()
            return chain.proceed(request)
        }

    }
}
