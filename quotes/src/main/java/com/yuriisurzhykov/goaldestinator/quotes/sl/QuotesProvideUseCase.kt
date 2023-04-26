package com.yuriisurzhykov.goaldestinator.quotes.sl

import com.yuriisurzhykov.goaldestinator.core.domain.UseCase
import com.yuriisurzhykov.goaldestinator.core.sl.ProvideRepository
import com.yuriisurzhykov.goaldestinator.core.sl.ProvideUseCase
import com.yuriisurzhykov.goaldestinator.quotes.data.QuotesRepository
import com.yuriisurzhykov.goaldestinator.quotes.domain.QuoteCacheToDomainMapper
import com.yuriisurzhykov.goaldestinator.quotes.domain.RandomQuoteUseCase

interface QuotesProvideUseCase : ProvideUseCase {

    class Base(
        private val provideRepository: ProvideRepository<QuotesRepository>,
        private val error: ProvideUseCase.Error = ProvideUseCase.Error()
    ) : QuotesProvideUseCase {

        @Suppress("UNCHECKED_CAST")
        override fun <T : UseCase> provide(clazz: Class<T>): T = when (clazz) {
            RandomQuoteUseCase::class.java -> RandomQuoteUseCase.Base(
                provideRepository.provide(),
                QuoteCacheToDomainMapper.Base()
            ) as T
            else -> error.provide(clazz)
        }
    }
}
