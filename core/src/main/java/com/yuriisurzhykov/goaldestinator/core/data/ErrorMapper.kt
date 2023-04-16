package com.yuriisurzhykov.goaldestinator.core.data

interface ErrorMapper<S : Throwable, O : Throwable> {

    fun map(source: S): O
}
