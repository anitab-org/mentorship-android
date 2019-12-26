package org.systers.mentorship.remote

import android.text.TextUtils
import androidx.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import org.systers.mentorship.utils.PreferenceManager

class CustomRefreshInterceptor : Interceptor {

    var preferenceManager: PreferenceManager = PreferenceManager()

    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val builder = chainRequest.newBuilder()

        val accessToken = preferenceManager.refreshToken

        if (!TextUtils.isEmpty(accessToken))
            builder.header("Authorization", accessToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}