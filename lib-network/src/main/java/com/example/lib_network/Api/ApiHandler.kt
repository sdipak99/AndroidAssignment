package com.example.lib_network.Api

import com.example.lib_network.Models.CustomUiResponse

object ApiHandler {
    val api by lazy { ApiClient().api }
    suspend fun <T:Any> fetchCustomUI(url:String):NetworkResult<T> {


            val response = api.getCustomUI(url)
            val body = response.body()
            if (response.isSuccessful && body != null)
                NetworkResult.Success(body);
            else
                NetworkResult.Error(response.code(), response.message())

        return NetworkResult.Success(body)



    }

    //todo - no endpoint was given
    //suspend fun fetchImage(url:String)
}