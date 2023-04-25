package com.yuriisurzhykov.goaldestinator.coretest.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class TestLifecycleOwner(
    private val testLifecycle: Lifecycle
) : LifecycleOwner {
    override val lifecycle: Lifecycle
        get() = testLifecycle
}
