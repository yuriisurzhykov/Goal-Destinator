package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import android.content.Context
import com.yuriisurzhykov.goaldestinator.core.data.Serialization

interface QuotesCacheDataSource {

    suspend fun quotes(): List<QuoteCache.Base>

    class Dao(
        private val dao: QuotesDao
    ) : QuotesCacheDataSource {
        override suspend fun quotes(): List<QuoteCache.Base> = dao.quotes()
    }

    class LocalSamples(
        private val context: Context,
        private val serialization: Serialization
    ) : QuotesCacheDataSource {

        override suspend fun quotes(): List<QuoteCache.Base> {
            return context.assets.open("samples.json").use {
                val string = it.readBytes().toString(Charsets.UTF_8)
                return@use serialization.fromJson(
                    string,
                    ArrayList::class.java
                ) as ArrayList<QuoteCache.Base>
            }
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
        context: Context,
        serialization: Serialization
    ) : WithAlternative(
        Dao(dao),
        LocalSamples(context, serialization)
    )
}
