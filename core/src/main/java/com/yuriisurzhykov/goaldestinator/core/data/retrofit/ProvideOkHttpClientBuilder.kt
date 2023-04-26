package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {

    fun provideClientBuilder(): OkHttpClient.Builder

    class Base(
        private val timeout: Long = 60,
        private val timeoutUnit: TimeUnit = TimeUnit.SECONDS
    ) : ProvideOkHttpClientBuilder {

        override fun provideClientBuilder(): OkHttpClient.Builder {
            return OkHttpClient.Builder()
                .callTimeout(timeout, timeoutUnit)
                .retryOnConnectionFailure(true)
                .readTimeout(timeout, timeoutUnit)
        }
    }

    class AddInterceptor(
        private val interceptor: Interceptor,
        private val provide: ProvideOkHttpClientBuilder
    ) : ProvideOkHttpClientBuilder {
        override fun provideClientBuilder() =
            provide.provideClientBuilder().addInterceptor(interceptor)
    }
}
