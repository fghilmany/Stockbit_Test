package com.fghilmany.stockbittest.core.domain.usecase

import com.fghilmany.stockbittest.core.data.Resource
import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface DataUseCase {

    fun getData() : Flow<Resource<DataResponse>>
}