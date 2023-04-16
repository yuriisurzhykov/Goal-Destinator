package com.yuriisurzhykov.goaldestinator.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface DispatcherCall {

    fun ui(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun io(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    abstract class Abstract(
        private val dispatchers: Dispatchers
    ) : DispatcherCall {
        override fun ui(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {
            return scope.launch(dispatchers.ui(), block = block)
        }

        override fun io(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {
            return scope.launch(dispatchers.io(), block = block)
        }
    }

    class Base : Abstract(Dispatchers.Base())
}