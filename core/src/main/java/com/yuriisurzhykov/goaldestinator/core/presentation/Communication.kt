package com.yuriisurzhykov.goaldestinator.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

interface Communication {

    interface Update<T : Any> : Communication {
        fun update(value: T)
    }

    interface Observe<T : Any> {
        fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutable<T : Any> : Observe<T>, Update<T>

    abstract class AbstractLiveData<T : Any>(
        private val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Mutable<T> {

        override fun update(value: T) {
            liveData.value = value
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(lifecycleOwner, observer)
        }
    }

    abstract class AbstractFlow<T : Any>(
        dispatchers: Dispatchers,
        private val default: T,
        private val flow: MutableStateFlow<T>,
        private val coroutineScope: CoroutineScope = CoroutineScope(dispatchers.ui())
    ) : Mutable<T> {

        override fun update(value: T) {
            flow.update { value }
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
            flow.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), default)
        }
    }
}
