package com.example.lib_network.Models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CustomUiResponse(
    @SerializedName("heading-text")
    val headingText: String,
    @SerializedName("logo-url")
    val logoUrl: String,
    @SerializedName("uidata")
    val uidata: List<Uidata>
):Serializable