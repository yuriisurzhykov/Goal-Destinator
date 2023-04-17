package com.yuriisurzhykov.goaldestinator.core.data

interface Save<T : Any> {

    fun save(key: String, value: T)
}
