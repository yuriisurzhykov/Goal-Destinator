package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    class Base(
        private val provideOkHttp: ProvideOkHttpClientBuilder,
        private val provideFactory: ProvideConverterFactory
    ) : ProvideRetrofitBuilder {

        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .client(provideOkHttp.provideClientBuilder().build())
            .addConverterFactory(provideFactory.provideConverterFactory())
    }
}
