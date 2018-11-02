package org.systers.mentorship.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.systers.mentorship.remote.services.AuthService
import org.systers.mentorship.remote.services.RelationService
import org.systers.mentorship.remote.services.UserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by murad on 7/26/18.
 */
class ApiManager {

    private lateinit var retrofit: Retrofit
    private lateinit var authService: AuthService
    private lateinit var relationService: RelationService
    private lateinit var userService: UserService

    init {
        initialiseRetrofit()
    }

    private fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private fun initServices() {
        authService = createService(AuthService::class.java)
        relationService = createService(RelationService::class.java)
        userService = createService(UserService::class.java)
    }

    private fun initialiseRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(CustomInterceptor())
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl.apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        initServices()
    }

    /**
     * @return a pointer to an initialised [AuthService]
     */
    fun getAuthService(): AuthService = authService

    /**
     * @return a pointer to an initialised [RelationService]
     */
    fun getMentorshipRelationService() = relationService

    /**
     * @return a pointer to an initialised [UserService]
     */
    fun getUserService(): UserService = userService
}
