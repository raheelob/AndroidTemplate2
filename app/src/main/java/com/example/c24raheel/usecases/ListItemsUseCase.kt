package com.example.c24raheel.usecases

import com.example.c24raheel.data.HomePageResponse
import com.example.c24raheel.repository.ListItemsRepository
import com.example.c24raheel.retrofit.NetworkExecutor
import com.example.c24raheel.retrofit.RemoteData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListItemsUseCase @Inject constructor(private val listItemsRepository: ListItemsRepository) :
    NetworkExecutor<ListItemsUseCase.Params, HomePageResponse>() {

    class Params

    /*override suspend fun buildUseCase(parameter: Params): Flow<RemoteData<HomePageResponse>> {
        return listItemsRepository.getItemsList()
    }*/

    override suspend fun runUseCase(parameters: Params?): Flow<RemoteData<HomePageResponse>> {
        return listItemsRepository.getItemsList()
    }
}