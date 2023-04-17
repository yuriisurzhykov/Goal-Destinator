package com.yuriisurzhykov.goaldestinator.core.data.retrofit

import com.google.gson.Gson

interface ProvideGson {
    fun provide(): Gson

    class Base : ProvideGson {
        override fun provide(): Gson = Gson()
    }
}
