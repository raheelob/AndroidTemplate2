package com.example.c24raheel.repository

import com.example.c24raheel.data.HomePageResponse
import com.example.c24raheel.retrofit.RemoteData
import kotlinx.coroutines.flow.Flow

interface ListItemsRepository {

    suspend fun getItemsList(): Flow<RemoteData<HomePageResponse>>
    suspend fun saveToWishlist(id: String)
    suspend fun removeFromWishlist(id: String)
}