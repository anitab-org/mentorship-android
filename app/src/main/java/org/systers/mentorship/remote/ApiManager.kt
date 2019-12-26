package org.systers.mentorship.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.systers.mentorship.remote.services.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A class that represents API Manager. It encapsulates three services: authentication, relation and user.
 */
class ApiManager {

    val authService: AuthService
    val relationService: RelationService
    val userService: UserService
    val taskService: TaskService
    val refreshService: RefreshService

    companion object {
        private var apiManager: ApiManager? = null

        val instance: ApiManager
            get() {
                if (apiManager == null) {
                    apiManager = ApiManager()
                }
                return apiManager as ApiManager
            }
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(CustomInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl.apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        authService = retrofit.create(AuthService::class.java)
        relationService = retrofit.create(RelationService::class.java)
        userService = retrofit.create(UserService::class.java)
        taskService = retrofit.create(TaskService::class.java)

        val okHttpClientRefresh = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(CustomRefreshInterceptor())
                .build()

        val retrofitRefresh = Retrofit.Builder()
                .baseUrl(BaseUrl.apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientRefresh)
                .build()

        refreshService = retrofitRefresh.create(RefreshService::class.java)
    }
}