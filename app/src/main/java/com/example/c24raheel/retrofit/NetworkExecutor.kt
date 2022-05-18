package com.example.c24raheel.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import okio.IOException
import retrofit2.HttpException

abstract class NetworkExecutor<in P, out R>() {

    /*suspend fun runUseCase(parameters: P?): Flow<RemoteData<R>> {}*/

    abstract suspend fun runUseCase(parameter: P?): Flow<RemoteData<R>>

    suspend fun execute(parameters: P?): Flow<RemoteData<R>> {
        return runUseCase(parameters).buffer().catch { e ->
            when (e) {
                is IOException -> emit(RemoteData.RemoteErrorByNetwork)
                is HttpException -> {
                    val code = e.code()
                    if (e.code() == 401) {
                        emit(RemoteData.Error401)
                    } else {
                        val errorResponse =
                            com.example.c24raheel.errorutils.ErrorConvertor.parseErrorBody(e)
                        emit(RemoteData.ErrorGeneral(code, errorResponse))
                    }
                }
                else -> {
                    emit(RemoteData.ErrorGeneral(null, null))
                }
            }
        }
    }
}