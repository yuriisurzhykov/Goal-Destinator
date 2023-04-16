package com.yuriisurzhykov.goaldestinator.core.data

interface ErrorHandler<I : Throwable, O : Any> {

    fun handle(source: I): O
}
