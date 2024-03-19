package com.yuriisurzhykov.goaldestinator.coretest

import com.yuriisurzhykov.goaldestinator.core.presentation.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestCoroutineDispatchers : Dispatchers {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()
    override fun ui(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
