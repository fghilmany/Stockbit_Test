package com.fghilmany.stockbittest.core.data.source.remote.network

import com.fghilmany.stockbittest.core.data.source.remote.response.DataResponse
import retrofit2.http.GET

interface ApiService {

    @GET("data/top/totaltoptiervolfull?limit=10&tsym=USD")
    suspend fun getData(): DataResponse

}