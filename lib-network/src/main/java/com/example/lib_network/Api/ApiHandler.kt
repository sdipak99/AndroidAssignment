package com.example.lib_network.Api

import com.example.lib_network.Models.CustomUiResponse
import retrofit2.HttpException


object ApiHandler {
    val api by lazy { ApiClient().api }
    suspend fun <T : Any> fetchCustomUI(url: String): NetworkResult<T> {
        return try {
                val response = api.getCustomUI(url)
                if(response.isSuccessful && response.body()!=null)
                    NetworkResult.Success(response.body() as CustomUiResponse)
                else
                    NetworkResult.Error(response.code(),response.message())

        } catch (e: HttpException) {
            NetworkResult.Error(e.code(),e.message)
        } catch (e:Throwable){
            NetworkResult.Exception(e)
        }
    }

    /*
    suspend fun <T : Any> fetchImage(url: String): NetworkResult<T> {
        //for the image data.
    }
    */
}