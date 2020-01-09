package org.systers.mentorship.remote

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control",
                            CacheControl.Builder()
                                    .maxAge(1, TimeUnit.HOURS)
                                    .build().toString())
                    .build()

}