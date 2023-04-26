package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import com.google.gson.Gson

interface ProvideGson {
    fun provideGson(): Gson

    class Base : ProvideGson {
        override fun provideGson(): Gson = Gson()
    }
}
