package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun provide(): OkHttpClient.Builder

    class Base(
        private val timeout: Long, private val timeoutUnit: TimeUnit = TimeUnit.SECONDS
    ) : ProvideOkHttpClientBuilder {

        override fun provide(): OkHttpClient.Builder {
            return OkHttpClient.Builder().callTimeout(timeout, timeoutUnit)
                .retryOnConnectionFailure(true).readTimeout(timeout, timeoutUnit)
        }
    }

    class AddInterceptor(
        private val interceptor: Interceptor,
        private val provide: ProvideOkHttpClientBuilder
    ) : ProvideOkHttpClientBuilder {
        override fun provide() = provide.provide().addInterceptor(interceptor)
    }
}
