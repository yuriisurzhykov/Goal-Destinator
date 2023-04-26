package com.yuriisurzhykov.goaldestinator.core.sl

import androidx.lifecycle.ViewModel

interface DependencyContainer {

    fun <T : ViewModel> module(clazz: Class<T>): Module<T>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): Module<T> {
            throw IllegalStateException("No module for class $clazz")
        }
    }
}
