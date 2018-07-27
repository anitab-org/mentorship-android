package org.systers.mentorship.remote

import android.support.annotation.NonNull
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.utils.PreferenceManager.Companion.AUTH_TOKEN

/**
 * Created by murad on 7/26/18.
 */
class CustomInterceptor: Interceptor {

    var preferenceManager: PreferenceManager = PreferenceManager()

    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val chainRequest = chain.request()
        val builder = chainRequest.newBuilder()

        val accessToken = preferenceManager.authToken

        if (!TextUtils.isEmpty(accessToken)) {
            builder.header(AUTH_TOKEN, preferenceManager.authToken)
        }

        val request = builder.build()
        return chain.proceed(request)
    }
}
