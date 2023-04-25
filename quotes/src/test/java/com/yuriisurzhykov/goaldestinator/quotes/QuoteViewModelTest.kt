package com.yuriisurzhykov.goaldestinator.quotes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.goaldestinator.coretest.TestCoroutineDispatcherCalls
import com.yuriisurzhykov.goaldestinator.coretest.TestCoroutineDispatchers
import com.yuriisurzhykov.goaldestinator.coretest.lifecycle.TestLifecycle
import com.yuriisurzhykov.goaldestinator.coretest.lifecycle.TestLifecycleOwner
import com.yuriisurzhykov.goaldestinator.quotes.domain.Quote
import com.yuriisurzhykov.goaldestinator.quotes.domain.RandomQuoteUseCase
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteCommunication
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class QuoteViewModelTest {

    @Test
    fun `view model init and recreate view`() {
        val dispatchers = TestCoroutineDispatcherCalls(TestCoroutineDispatchers())
        val useCase = TestQuotesUseCase()
        val communication = TestQuoteCommunication()
        val viewModel = QuoteViewModel(dispatchers, useCase, communication)
        viewModel.observe(TestLifecycleOwner(TestLifecycle(Lifecycle.State.RESUMED))) {

        }
        assertEquals(1, communication.observeCounts)
        assertEquals(1, communication.changesCount)
        viewModel.observe(TestLifecycleOwner(TestLifecycle(Lifecycle.State.RESUMED))) {

        }
        assertEquals(2, communication.observeCounts)
        assertEquals(1, communication.changesCount)
        assertEquals(useCase.quote, communication.value)
    }
}

private class TestQuoteCommunication : QuoteCommunication.Mutable {

    var observeCounts = 0
    var changesCount = 0
    var value: Quote? = null

    override fun update(value: Quote) {
        this.value = value
        changesCount++
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Quote>) {
        observer.onChanged(value!!)
        observeCounts++
    }
}

private class TestQuotesUseCase : RandomQuoteUseCase {

    val quote = Quote.Base(
        "Yurii",
        "Sasdui slkdal asdaisd"
    )

    override suspend fun quote(): Quote = quote
}
