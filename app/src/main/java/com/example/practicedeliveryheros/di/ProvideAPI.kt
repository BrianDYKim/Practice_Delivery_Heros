package com.example.practicedeliveryheros.di

import com.example.practicedeliveryheros.data.network.MapApiService
import com.example.practicedeliveryheros.data.url.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideOkHttpClient() : OkHttpClient {
    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    if(BuildConfig.DEBUG)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    else
        interceptor.level = HttpLoggingInterceptor.Level.NONE

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()
}

fun provideMapRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.TMAP_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideMapApiService(retrofit: Retrofit): MapApiService {
    return retrofit.create(MapApiService::class.java)
}