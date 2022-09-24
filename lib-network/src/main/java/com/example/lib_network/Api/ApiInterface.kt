package com.example.lib_network.Api

import com.example.lib_network.Models.CustomUiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getCustomUI(@Url url:String): Response<CustomUiResponse>
}