package com.yuriisurzhykov.goaldestinator.core.data.cache

interface StringStorage {

    fun save(key: String, value: String)

    fun read(key: String, default: String): String
}
