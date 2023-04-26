package com.yuriisurzhykov.goaldestinator.core.sl

interface ProvideRepository<T : Any> {

    fun provide(): T
}
