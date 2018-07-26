package org.systers.mentorship.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.systers.mentorship.remote.services.AuthService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by murad on 7/26/18.
 */
class ApiManager {

    private lateinit var retrofit: Retrofit
    private lateinit var authService: AuthService

    init {
        initialiseRetrofit()
    }

    private fun <T> createApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    fun initServices() {
        authService = createApi(AuthService::class.java)
    }

    private fun initialiseRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(CustomInterceptor())
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl.getBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        initServices()
    }

    fun getAuthService(): AuthService {
        return authService
    }
}
