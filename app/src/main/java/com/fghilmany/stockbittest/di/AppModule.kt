package com.fghilmany.stockbittest.di

import com.fghilmany.stockbittest.core.domain.usecase.DataInteractor
import com.fghilmany.stockbittest.core.domain.usecase.DataUseCase
import com.fghilmany.stockbittest.ui.watchlist.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get()) }
}

val viewModelModule = module {
    viewModel { WatchlistViewModel(get()) }
}