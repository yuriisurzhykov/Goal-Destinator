package com.yuriisurzhykov.goaldestinator.quotes.data

import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.Quote
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesList

interface QuoteCloudListToCacheListMapper : QuotesList.Mapper<List<QuoteCache.Base>> {

    class Base(
        private val mapper: QuoteCloudToCacheMapper
    ) : QuoteCloudListToCacheListMapper {

        override fun map(
            count: Int,
            totalCount: Int,
            page: Int,
            totalPages: Int,
            lastItemIndex: Int,
            results: List<Quote.Base>
        ): List<QuoteCache.Base> {
            return results.map { it.map(mapper) }
        }
    }
}
