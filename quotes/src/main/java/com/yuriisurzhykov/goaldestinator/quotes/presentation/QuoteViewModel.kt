package com.yuriisurzhykov.goaldestinator.quotes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.goaldestinator.core.presentation.DispatcherCall
import com.yuriisurzhykov.goaldestinator.quotes.domain.Quote
import com.yuriisurzhykov.goaldestinator.quotes.domain.RandomQuoteUseCase

class QuoteViewModel(
    dispatchers: DispatcherCall,
    private val quoteUseCase: RandomQuoteUseCase,
    private val quoteCommunication: QuoteCommunication.Mutable
) : ViewModel(), QuoteCommunication.Observe {

    init {
        dispatchers.io(viewModelScope) {
            val quote = quoteUseCase.quote()
            quoteCommunication.update(quote)
        }
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Quote>) {
        quoteCommunication.observe(lifecycleOwner, observer)
    }
}
