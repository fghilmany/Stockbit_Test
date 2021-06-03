package com.fghilmany.stockbittest.core.data.source.remote

import android.util.Log
import com.fghilmany.stockbittest.core.data.source.remote.network.ApiResponse
import com.fghilmany.stockbittest.core.data.source.remote.network.ApiService
import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(
    private val apiService: ApiService?
) {

    suspend fun getData(): Flow<ApiResponse<DataResponse>>{
        return flow{
            try {
                val response = apiService?.getData()
                if (response != null){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: HttpException){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}