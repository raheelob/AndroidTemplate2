package com.example.c24raheel.retrofit

import com.example.c24raheel.data.HomePageResponse
import retrofit2.http.GET

interface RetroFitAPICalls {
    @GET("http://m.check24.de/products-test.json")
    suspend fun getListItems(
    ): HomePageResponse
}