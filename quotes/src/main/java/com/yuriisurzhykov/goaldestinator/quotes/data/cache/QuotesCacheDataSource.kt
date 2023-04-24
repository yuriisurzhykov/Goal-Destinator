package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.StringProvider

interface QuotesCacheDataSource {

    suspend fun quotes(): List<QuoteCache.Base>

    class Dao(
        private val dao: QuotesDao
    ) : QuotesCacheDataSource {
        override suspend fun quotes(): List<QuoteCache.Base> = dao.quotes()
    }

    class LocalSamples(
        private val stringProvider: StringProvider,
        private val serialization: Serialization
    ) : QuotesCacheDataSource {

        override suspend fun quotes(): List<QuoteCache.Base> {
            return serialization.fromJson(
                stringProvider.provide(),
                ArrayList::class.java
            ) as ArrayList<QuoteCache.Base>
        }
    }

    open class WithAlternative(
        private val main: QuotesCacheDataSource,
        private val alternative: QuotesCacheDataSource
    ) : QuotesCacheDataSource {
        override suspend fun quotes(): List<QuoteCache.Base> {
            val mainList = main.quotes()
            return mainList.ifEmpty { alternative.quotes() }
        }
    }

    class Base(
        dao: QuotesDao,
        context: StringProvider,
        serialization: Serialization
    ) : WithAlternative(
        Dao(dao),
        LocalSamples(context, serialization)
    )
}
