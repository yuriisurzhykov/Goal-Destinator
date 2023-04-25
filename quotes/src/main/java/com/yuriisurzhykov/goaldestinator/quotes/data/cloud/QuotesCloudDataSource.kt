package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import com.yuriisurzhykov.goaldestinator.core.data.DataSource
import com.yuriisurzhykov.goaldestinator.core.data.cloud.AbstractCloudDataSource

interface QuotesCloudDataSource : DataSource<QuotesList.Base> {

    abstract class Abstract(
        private val quotesService: QuotesService,
        private val tags: List<String>
    ) : AbstractCloudDataSource("/quotes"), QuotesCloudDataSource {

        override suspend fun data(): QuotesList.Base {
            return handle {
                quotesService.quotes(tags.joinToString(","))
            }
        }
    }

    class Motivation(service: QuotesService) : Abstract(service, listOf("motivational"))
}
