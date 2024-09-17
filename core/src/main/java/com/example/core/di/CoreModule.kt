package com.example.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.utils.ResourceProvider
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/1d9b332c-d248-45f1-95a8-2b66f21498b8/"

val coreModule = module {
    single {

        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { ResourceProvider(get()) }
}