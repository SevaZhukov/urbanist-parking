package com.urbanist.parking.core.network

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class RetrofitModule {

    private val okHttpClient = OkHttpClient.Builder()
        .followSslRedirects(true)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        @Named("baseUrl") baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}