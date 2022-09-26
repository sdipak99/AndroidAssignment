package com.example.androidassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.Api.ApiHandler
import com.example.lib_network.Api.NetworkResult
import com.example.lib_network.Models.CustomUiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel :ViewModel() {
    val errorResponse = MutableLiveData<String>()
    val customUiObject = MutableLiveData<CustomUiResponse>()
    fun fetchCustomUI(url:String) = viewModelScope.launch(Dispatchers.IO) {
        when(val response = ApiHandler.fetchCustomUI<NetworkResult<CustomUiResponse>>(url)){
            is NetworkResult.Success -> customUiObject.postValue(response.data as CustomUiResponse)
            is NetworkResult.Error -> errorResponse.postValue(response.message?:"")
            is NetworkResult.Exception -> errorResponse.postValue(response.e.message)
        }
    }
}