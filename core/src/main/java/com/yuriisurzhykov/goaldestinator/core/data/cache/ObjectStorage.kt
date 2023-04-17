package com.yuriisurzhykov.goaldestinator.core.data.cache

import com.yuriisurzhykov.goaldestinator.core.data.Serialization

interface ObjectStorage {

    fun save(key: String, value: Any)

    fun <T : Any> read(key: String, default: T): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage
    ) : ObjectStorage {

        override fun save(key: String, value: Any) {
            val json = serialization.toJson(value)
            stringStorage.save(key, json)
        }

        override fun <T : Any> read(key: String, default: T): T {
            val defaultJson = serialization.toJson(default)
            val json = stringStorage.read(key, defaultJson)
            return serialization.fromJson(json, default::class.java)
        }
    }
}
