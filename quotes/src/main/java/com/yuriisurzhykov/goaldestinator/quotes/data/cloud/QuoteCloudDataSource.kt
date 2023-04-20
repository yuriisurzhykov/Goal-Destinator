package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import com.yuriisurzhykov.goaldestinator.core.data.DataSource
import com.yuriisurzhykov.goaldestinator.core.data.cloud.AbstractCloudDataSource

interface QuoteCloudDataSource : DataSource<Quote> {

    abstract class Abstract(
        private val quotesService: QuotesService,
        private val tags: List<String>
    ) : AbstractCloudDataSource(), QuoteCloudDataSource {

        override suspend fun data(): Quote {
            return handle {
                quotesService.randomQuote(tags.joinToString(","))
            }
        }
    }

    class Motivation(service: QuotesService) : Abstract(service, listOf("motivation"))
}