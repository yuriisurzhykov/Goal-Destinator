package com.yuriisurzhykov.goaldestinator.quotes.sl

import androidx.lifecycle.ViewModel
import com.yuriisurzhykov.goaldestinator.core.presentation.DispatcherCall
import com.yuriisurzhykov.goaldestinator.core.sl.DependencyContainer
import com.yuriisurzhykov.goaldestinator.core.sl.Module
import com.yuriisurzhykov.goaldestinator.quotes.domain.RandomQuoteUseCase
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteViewModel

interface QuoteDependencyContainer : DependencyContainer {

    class Base(
        private val dispatcherCall: DispatcherCall,
        private val provideUseCase: QuotesProvideUseCase,
        private val error: DependencyContainer = DependencyContainer.Error()
    ) : QuoteDependencyContainer {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> module(clazz: Class<T>): Module<T> = when (clazz) {
            QuoteViewModel::class.java -> QuoteModule(
                dispatcherCall,
                provideUseCase.provide(RandomQuoteUseCase::class.java)
            ) as Module<T>
            else -> error.module(clazz)
        }
    }
}
