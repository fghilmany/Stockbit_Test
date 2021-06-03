package com.fghilmany.stockbittest.core.data

import com.fghilmany.stockbittest.core.data.source.remote.RemoteDataSource
import com.fghilmany.stockbittest.core.data.source.remote.network.ApiResponse
import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import com.fghilmany.stockbittest.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remoteDataSource: RemoteDataSource
): IDataRepository {
    override fun getData(): Flow<Resource<DataResponse>> {
        return object : OnlineResource<DataResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<DataResponse>> {
                return remoteDataSource.getData()
            }
        }.asFlow()
    }
}