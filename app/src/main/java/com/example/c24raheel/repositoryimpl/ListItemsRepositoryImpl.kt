package com.example.c24raheel.repositoryimpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.c24raheel.data.HomePageResponse
import com.example.c24raheel.repository.ListItemsRepository
import com.example.c24raheel.retrofit.RemoteData
import com.example.c24raheel.retrofit.RetroFitAPICalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListItemsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val listItemsApi: RetroFitAPICalls
) :
    ListItemsRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "wishlist")

    override suspend fun getItemsList(
    ): Flow<RemoteData<HomePageResponse>> =
        flow {
            val response = listItemsApi.getListItems()
            response.products?.forEach { item ->
                context.datastore.edit {
                    item.id?.let { itemId ->
                        // checking if this item added to favorites so change the saved boolean.
                        if (it.contains(booleanPreferencesKey("$itemId"))) {
                            item.saved = true
                        }
                    }

                }
            }
            emit(RemoteData.Success(response))
        }.flowOn(Dispatchers.IO)

    // save the item id to datastore
    override suspend fun saveToWishlist(id: String) {
        context.datastore.edit {
            it[booleanPreferencesKey(id)] = true
        }
    }

    // remove the item id to datastore
    override suspend fun removeFromWishlist(id: String) {
        context.datastore.edit {
            it.remove(booleanPreferencesKey(id))
        }
    }

}