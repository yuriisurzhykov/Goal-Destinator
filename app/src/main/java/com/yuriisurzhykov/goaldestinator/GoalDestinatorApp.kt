package com.yuriisurzhykov.goaldestinator

import android.app.Application
import com.yuriisurzhykov.goaldestinator.quotes.sl.QuoteDependencyContainer
import com.yuriisurzhykov.goaldestinator.quotes.sl.QuotesCoreModule

class GoalDestinatorApp : Application(), QuotesCoreModule {

    override fun provideQuoteDependencyContainer(): QuoteDependencyContainer {
        return QuotesCoreModule.Base(this).provideQuoteDependencyContainer()
    }
}
