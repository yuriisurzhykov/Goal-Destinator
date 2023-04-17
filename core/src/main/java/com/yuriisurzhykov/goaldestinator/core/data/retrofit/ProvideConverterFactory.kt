package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

interface ProvideConverterFactory {

    fun provide(): Converter.Factory

    class Gson(
        private val provideGson: ProvideGson
    ) : ProvideConverterFactory {

        override fun provide(): Converter.Factory =
            GsonConverterFactory.create(provideGson.provide())
    }
}
