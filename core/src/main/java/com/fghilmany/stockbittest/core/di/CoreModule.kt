package com.fghilmany.stockbittest.core.di

import com.fghilmany.stockbittest.core.BuildConfig
import com.fghilmany.stockbittest.core.data.DataRepository
import com.fghilmany.stockbittest.core.data.source.remote.RemoteDataSource
import com.fghilmany.stockbittest.core.data.source.remote.network.ApiService
import com.fghilmany.stockbittest.core.domain.repository.IDataRepository
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "tourism-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

}
val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IDataRepository> { DataRepository(get()) }
}
