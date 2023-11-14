package com.android.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.android.network.services.ApiService
import com.android.network.services.SynchronousCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By Khizzar
 * Created On 11/11/2023
 **/

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    private const val TIMEOUT = 15L

//    private fun provideOkHttpClient() : OkHttpClient {
//        return OkHttpClient.Builder()
//            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideRetrofitService(): ApiService {
//        val gson: Gson = GsonBuilder()
//            .setLenient()
//            .disableHtmlEscaping()
//            .create()
//
//        return Retrofit.Builder()
//            .baseUrl("https://survey-api.nimblehq.co")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(provideOkHttpClient())
//            .build()
//            .create(ApiService::class.java)
//    }



    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://survey-api.nimblehq.co")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }



}