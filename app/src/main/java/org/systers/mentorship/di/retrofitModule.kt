package org.systers.mentorship.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.systers.mentorship.remote.BaseUrl
import org.systers.mentorship.remote.CustomInterceptor
import org.systers.mentorship.remote.TokenAuthenticator
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class retrofitModule {
    @Provides
    fun providesInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    @Provides
    fun providesOkHttpClient(interceptor : HttpLoggingInterceptor ,
                             customInterceptor: CustomInterceptor ,
                             authenticator: TokenAuthenticator): OkHttpClient {
    return   OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(customInterceptor)
            .authenticator(authenticator)
            .build()
    }
    @Provides
    fun providesGSON(): Gson {
    return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
    @Provides
    fun provideRetrofit(gson : Gson , okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BaseUrl.apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


}