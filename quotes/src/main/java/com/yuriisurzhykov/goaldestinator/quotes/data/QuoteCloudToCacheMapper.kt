package com.yuriisurzhykov.goaldestinator.quotes.data

import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.Quote

interface QuoteCloudToCacheMapper : Quote.Mapper<QuoteCache.Base> {

    class Base : QuoteCloudToCacheMapper {

        override fun map(
            id: String,
            content: String,
            author: String,
            authorSlug: String,
            length: Long,
            tags: List<String>,
            dateModified: String
        ) = QuoteCache.Base(id, author, content, dateModified)
    }
}
