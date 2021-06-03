package com.fghilmany.stockbittest.core.domain.usecase

import com.fghilmany.stockbittest.core.data.Resource
import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import com.fghilmany.stockbittest.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow

class DataInteractor(private val dataRepository: IDataRepository): DataUseCase {
    override fun getData(): Flow<Resource<DataResponse>> {
        return dataRepository.getData()
    }
}