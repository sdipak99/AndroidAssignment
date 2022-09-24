package com.example.androidassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.Api.ApiHandler
import com.example.lib_network.Models.CustomUiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel :ViewModel() {
    val customUiObject = MutableLiveData<CustomUiResponse>()
    fun fetchCustomUI(url:String) = viewModelScope.launch(Dispatchers.IO) {
        //customUiObject.postValue(ApiHandler.fetchCustomUI(url))
    }
}