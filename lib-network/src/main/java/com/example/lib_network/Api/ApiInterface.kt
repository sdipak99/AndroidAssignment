package com.example.lib_network.Api

import com.example.lib_network.Models.CustomUiResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("android_assignment.json")
    suspend fun getCustomUI():CustomUiResponse
}