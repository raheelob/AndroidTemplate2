package com.example.c24raheel.errorutils

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null
)