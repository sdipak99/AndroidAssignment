package com.example.lib_network.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {

    val api by lazy { retrofit.create(ApiInterface::class.java) }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val httpClient by lazy{
        OkHttpClient.Builder()
            .addInterceptor{chain -> chain.proceed(chain.request())}
            .build()
    }
}