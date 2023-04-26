package com.yuriisurzhykov.goaldestinator.quotes.domain

import com.yuriisurzhykov.goaldestinator.core.domain.UseCase
import com.yuriisurzhykov.goaldestinator.quotes.data.QuotesRepository

interface RandomQuoteUseCase : UseCase {

    suspend fun quote(): Quote

    class Base(
        private val repository: QuotesRepository,
        private val mapper: QuoteCacheToDomainMapper
    ) : RandomQuoteUseCase {

        override suspend fun quote(): Quote {
            return repository.quotes().random().map(mapper)
        }
    }
}
