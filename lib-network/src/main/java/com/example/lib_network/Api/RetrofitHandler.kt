package com.example.lib_network.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.logging.Level.ALL

class ApiClient {

    val api by lazy { retrofit.create(ApiInterface::class.java) }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://demo.ezetap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val httpClient by lazy{

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()
    }
}