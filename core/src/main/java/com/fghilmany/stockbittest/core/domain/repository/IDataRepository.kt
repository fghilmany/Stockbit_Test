package com.fghilmany.stockbittest.core.domain.repository

import com.fghilmany.stockbittest.core.data.Resource
import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getData() : Flow<Resource<DataResponse>>

}