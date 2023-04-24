package com.yuriisurzhykov.goaldestinator.core.data

import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import java.lang.reflect.Type

interface Serialization {

    fun toJson(source: Any): String
    fun <T> fromJson(json: String, clazz: Class<T>): T
    fun <T> fromJson(json: String, type: Type): T

    class Base(
        provideGson: ProvideGson
    ) : Serialization {

        private val gson by lazy { provideGson.provide() }

        override fun toJson(source: Any): String = gson.toJson(source)

        override fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson(json, clazz)
        override fun <T> fromJson(json: String, type: Type): T = gson.fromJson(json, type)
    }
}
