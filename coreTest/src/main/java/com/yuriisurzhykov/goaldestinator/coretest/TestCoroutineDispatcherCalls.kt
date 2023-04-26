package com.yuriisurzhykov.goaldestinator.coretest

import com.yuriisurzhykov.goaldestinator.core.presentation.DispatcherCall
import com.yuriisurzhykov.goaldestinator.core.presentation.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestCoroutineDispatcherCalls(
    private val dispatchers: Dispatchers
) : DispatcherCall {

    override fun ui(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {
        return scope.launch(dispatchers.ui(), block = block)
    }

    override fun io(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {
        return scope.launch(dispatchers.io(), block = block)
    }

    override suspend fun switchUi(block: suspend CoroutineScope.() -> Unit) {
        withContext(dispatchers.ui(), block = block)
    }
}
