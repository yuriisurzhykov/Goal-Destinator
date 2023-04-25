package com.yuriisurzhykov.goaldestinator.quotes.domain

import com.yuriisurzhykov.goaldestinator.quotes.data.QuotesRepository

interface RandomQuoteUseCase {

    suspend fun quote(): Quote

    class Base(
        private val repository: QuotesRepository,
        private val mapper: QuoteCacheToDomainMapper
    ) : RandomQuoteUseCase {

        override suspend fun quote(): Quote = repository.quotes().random().map(mapper)
    }
}
