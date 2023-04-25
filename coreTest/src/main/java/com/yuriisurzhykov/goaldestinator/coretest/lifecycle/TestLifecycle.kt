package com.yuriisurzhykov.goaldestinator.coretest.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

class TestLifecycle(
    private val state: State
) : Lifecycle() {

    override val currentState: State
        get() = state

    override fun addObserver(observer: LifecycleObserver) {
    }

    override fun removeObserver(observer: LifecycleObserver) {
    }
}
