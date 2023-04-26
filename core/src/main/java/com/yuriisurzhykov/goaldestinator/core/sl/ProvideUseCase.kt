package com.yuriisurzhykov.goaldestinator.core.sl

import com.yuriisurzhykov.goaldestinator.core.domain.UseCase

interface ProvideUseCase {

    fun <T : UseCase> provide(clazz: Class<T>): T

    class Error : ProvideUseCase {
        override fun <T : UseCase> provide(clazz: Class<T>): T {
            throw IllegalAccessException("There is no provided use case for class: $clazz!")
        }
    }
}
