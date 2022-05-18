package com.example.c24raheel.retrofit
import java.lang.Exception

sealed class ResponseWrapper <out T : Any> {
    data class Success <T: Any>(val data : T) : ResponseWrapper<T>()
    data class Error(var exception: Exception) : ResponseWrapper<Nothing>()
    object RemoteErrorByNetwork : RemoteData<Nothing>()
    object Loading : RemoteData<Nothing>()
    object Error401: RemoteData<Nothing>()

}