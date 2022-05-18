package com.example.c24raheel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c24raheel.data.HomePageResponse
import com.example.c24raheel.retrofit.RemoteData
import com.example.c24raheel.usecases.ListItemsUseCase
import com.example.c24raheel.utils.StateLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: ListItemsUseCase
) : ViewModel() {

    private var _listItemsResponseSLD: StateLiveData<HomePageResponse> = StateLiveData()
    val listItemsResponseSLD: StateLiveData<HomePageResponse> get() = _listItemsResponseSLD


    fun getItemsList() = viewModelScope.launch {
//        showLoading.value = true
        useCase.execute(ListItemsUseCase.Params()).collect { res ->
            when (res) {
                RemoteData.Loading -> {
                    _listItemsResponseSLD.postLoading()
                }

                is RemoteData.Success -> {
                    res?.value.let {
                        _listItemsResponseSLD.postSuccess(it)
                    }
                }

                is RemoteData.RemoteErrorByNetwork -> {
                    _listItemsResponseSLD.postLoading()
                }

                is RemoteData.Error401 -> {
                    _listItemsResponseSLD.postLoading()
                }

                is RemoteData.ErrorGeneral -> {
                    _listItemsResponseSLD.postLoading()
                }
            }

        }

        /* useCase.execute(ListItemsUseCase.Params()).collect { response ->
             showLoading.value = false
             when (response) {
                 is DataState.GenericError -> {
                     response.error?.errorResponse?.errorMessage?.let { err ->
                         showError.value = err
                     }
                 }
                 is DataState.Success -> {
                     response.value?.let { response ->
                         _listItemsResponse.value = response
                     }
                 }
             }
         }*/
    }

//    fun saveItem(listItem: ListItem) = viewModelScope.launch {
//        listItem.id?.let { saveItemUseCase.saveItem(it) }
//        val currentList = _wishListItems.value as MutableList
//        currentList.add(listItem)
//
//        _wishListItems.value = currentList
//    }
//
//    fun removeItem(listItem: ListItem) = viewModelScope.launch {
//        listItem.id?.let { saveItemUseCase.removeItem(it) }
//
//        val currentList = _wishListItems.value as MutableList
//        currentList.remove(listItem)
//
//        _wishListItems.value = currentList
//    }

}