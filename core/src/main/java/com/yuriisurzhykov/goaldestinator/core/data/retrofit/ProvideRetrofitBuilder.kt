package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provide(): Retrofit.Builder

    class Base(
        private val provideOkHttp: ProvideOkHttpClientBuilder,
        private val provideFactory: ProvideConverterFactory
    ) : ProvideRetrofitBuilder {

        override fun provide(): Retrofit.Builder = Retrofit.Builder()
            .client(provideOkHttp.provide().build())
            .addConverterFactory(provideFactory.provide())
    }
}
