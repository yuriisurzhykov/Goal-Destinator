package com.yuriisurzhykov.goaldestinator.coretest

import androidx.lifecycle.Observer

class TestEmptyObserver<T : Any> : Observer<T> {
    override fun onChanged(value: T) = Unit
}
