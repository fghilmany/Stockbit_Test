package com.fghilmany.stockbittest

import android.app.Application
import com.fghilmany.stockbittest.core.di.networkModule
import com.fghilmany.stockbittest.core.di.repositoryModule
import com.fghilmany.stockbittest.di.useCaseModule
import com.fghilmany.stockbittest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}