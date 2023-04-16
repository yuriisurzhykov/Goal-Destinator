package com.yuriisurzhykov.goaldestinator.core.data

interface DataSource<T : Any> {

    suspend fun data(): T
}
