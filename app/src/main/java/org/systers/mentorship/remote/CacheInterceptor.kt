package org.systers.mentorship.remote

import androidx.annotation.NonNull
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants.CACHE_CONTROL_MAX_AGE
import java.util.concurrent.TimeUnit

/**
 * Represents a custom HTTP cache network interceptor
 * This interceptor caches the response for a particular duration to reduce API calls to the server.
 */
class CacheInterceptor: Interceptor {

    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val context = MentorshipApplication.getContext()
        val response = chain.proceed(chain.request())

        /**
         * Setting the `maxAge` property of Cache, which specifies the time limit for which Cached results will hold the data.
         * */
        val cacheControl = CacheControl.Builder()
                                                    .maxAge(CACHE_CONTROL_MAX_AGE, TimeUnit.MINUTES)
                                                    .build()

        return response.newBuilder()
                .removeHeader(context.getString(R.string.pragma_header))
                .removeHeader(context.getString(R.string.cache_control_header))
                .header(context.getString(R.string.cache_control_header), cacheControl.toString())
                .build()
    }
}
