package com.example.androidassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lib_network.Api.ApiHandler

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel:HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")

        viewModel.customUiObject.observe(this){

        }
    }
}