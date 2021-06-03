package com.fghilmany.stockbittest.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fghilmany.stockbittest.core.domain.usecase.DataUseCase

class WatchlistViewModel(dataUseCase: DataUseCase): ViewModel() {

    val getWatchList = dataUseCase.getData().asLiveData()

}