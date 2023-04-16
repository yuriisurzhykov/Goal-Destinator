package com.yuriisurzhykov.goaldestinator.core.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {

    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher

    class Base : Dispatchers {
        override fun io(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
        override fun ui(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    }
}