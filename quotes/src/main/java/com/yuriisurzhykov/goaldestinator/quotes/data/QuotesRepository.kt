package com.yuriisurzhykov.goaldestinator.quotes.data

import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesCacheDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDao
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesCloudDataSource

interface QuotesRepository {

    suspend fun quotes(): List<QuoteCache>

    abstract class Abstract(
        private val quotesCacheDataSource: QuotesCacheDataSource,
        private val quotesCloudDataSource: QuotesCloudDataSource,
        private val quoteCacheToCloudMapper: QuoteCloudListToCacheListMapper,
        private val quotesDao: QuotesDao
    ) : QuotesRepository {

        override suspend fun quotes(): List<QuoteCache> {
            return try {
                val cloud = quotesCloudDataSource.data().map(quoteCacheToCloudMapper)
                quotesDao.insertAll(cloud)
                cloud
            } catch (e: Exception) {
                quotesCacheDataSource.quotes()
            }
        }
    }

    class Base(
        cache: QuotesCacheDataSource,
        cloud: QuotesCloudDataSource,
        mapper: QuoteCloudListToCacheListMapper,
        dao: QuotesDao
    ) : Abstract(cache, cloud, mapper, dao)
}
