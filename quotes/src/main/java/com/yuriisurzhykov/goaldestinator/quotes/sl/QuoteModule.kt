package com.yuriisurzhykov.goaldestinator.quotes.sl

import com.yuriisurzhykov.goaldestinator.core.presentation.DispatcherCall
import com.yuriisurzhykov.goaldestinator.core.sl.Module
import com.yuriisurzhykov.goaldestinator.quotes.domain.RandomQuoteUseCase
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteCommunication
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteViewModel

class QuoteModule(
    private val dispatchers: DispatcherCall,
    private val useCase: RandomQuoteUseCase
) : Module<QuoteViewModel> {

    override fun viewModel(): QuoteViewModel {
        return QuoteViewModel(dispatchers, useCase, QuoteCommunication.Base())
    }
}
