package com.yuriisurzhykov.goaldestinator.core.sl

import androidx.lifecycle.ViewModel

interface DependencyContainer {

    fun <T : ViewModel> viewModel(clazz: Class<T>): ProvideViewModel
}
