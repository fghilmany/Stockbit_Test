package com.fghilmany.stockbittest.core.domain.usecase

import com.fghilmany.stockbittest.core.data.Resource
import com.fghilmany.stockbittest.core.data.source.remote.response.*
import com.fghilmany.stockbittest.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataUsecaseTest {

    private lateinit var dataUseCase: DataUseCase

    @Mock
    private lateinit var dataRepository: IDataRepository

    @Before
    fun setUp(){
        dataUseCase = DataInteractor(dataRepository)
        val dummyData : Flow<Resource<DataResponse>> = flow { Resource.Success(DATA) }

        `when`(dataRepository.getData()).thenReturn(dummyData)
    }

    @Test
    fun getData() {
        dataUseCase.getData()
        verify(dataRepository).getData()
        verifyNoMoreInteractions(dataRepository)
    }

    companion object{

        private val listData : MutableList<DataItem> = mutableListOf()
        private val listSponsoredData : MutableList<SponsoredDataItem> = mutableListOf()


        val DATA = DataResponse(
            RateLimit(
                ""
            ),
            0,
            "",
            MetaData(
                0
            ),
            false,
            listData,
            listSponsoredData,
        )
    }
}