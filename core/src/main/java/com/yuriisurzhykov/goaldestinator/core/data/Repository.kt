package com.yuriisurzhykov.goaldestinator.core.data

import kotlinx.coroutines.flow.Flow

interface Repository<T : Any> {

    suspend fun data(): Flow<T>
}
