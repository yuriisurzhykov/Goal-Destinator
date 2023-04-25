package com.yuriisurzhykov.goaldestinator.quotes.domain

import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache

interface QuoteCacheToDomainMapper : QuoteCache.Mapper<Quote> {

    class Base : QuoteCacheToDomainMapper {
        override fun map(
            id: String,
            author: String,
            content: String,
            lastModified: String
        ) = Quote.Base(author, content)
    }
}