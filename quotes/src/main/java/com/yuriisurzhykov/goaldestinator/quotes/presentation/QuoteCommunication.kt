package com.yuriisurzhykov.goaldestinator.quotes.presentation

import com.yuriisurzhykov.goaldestinator.core.presentation.Communication
import com.yuriisurzhykov.goaldestinator.quotes.domain.Quote

interface QuoteCommunication : Communication {

    interface Update : Communication.Update<Quote>

    interface Observe : Communication.Observe<Quote>

    interface Mutable : Communication.Mutable<Quote>

    class Base : Communication.AbstractLiveData<Quote>(), Observe, Update, Mutable
}