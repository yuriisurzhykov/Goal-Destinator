package com.yuriisurzhykov.goaldestinator.core.data

interface Read<T : Any> {

    fun read(key: String, default: T): T
}
