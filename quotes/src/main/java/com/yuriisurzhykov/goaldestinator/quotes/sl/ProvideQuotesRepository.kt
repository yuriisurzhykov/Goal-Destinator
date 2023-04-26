package com.yuriisurzhykov.goaldestinator.quotes.sl

import android.content.Context
import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.StringProvider
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.CreateService
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import com.yuriisurzhykov.goaldestinator.core.sl.ProvideRepository
import com.yuriisurzhykov.goaldestinator.quotes.data.QuoteCloudListToCacheListMapper
import com.yuriisurzhykov.goaldestinator.quotes.data.QuoteCloudToCacheMapper
import com.yuriisurzhykov.goaldestinator.quotes.data.QuotesRepository
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesCacheDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDao
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesCloudDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesService
import com.yuriisurzhykov.goaldestinator.quotes.domain.SamplesStringProvider


interface ProvideQuotesRepository : ProvideRepository<QuotesRepository> {

    class Base(
        private val provideDao: ProvideDao,
        private val createService: CreateService,
        context: Context
    ) : ProvideQuotesRepository {

        private val stringProvider: StringProvider = SamplesStringProvider.Base(context)
        private val serialization = Serialization.Base(ProvideGson.Base())
        private val quoteCloudToCacheMapper: QuoteCloudListToCacheListMapper =
            QuoteCloudListToCacheListMapper.Base(QuoteCloudToCacheMapper.Base())

        private fun quoteCacheDataSource(): QuotesCacheDataSource {
            return QuotesCacheDataSource.Base(
                provideDao.provide(QuotesDao::class.java),
                stringProvider,
                serialization
            )
        }

        private fun quotesCloudDataSource(): QuotesCloudDataSource {
            return QuotesCloudDataSource
                .Motivation(createService.create(QuotesService::class.java))
        }

        override fun provide(): QuotesRepository {
            return QuotesRepository.Base(
                quoteCacheDataSource(),
                quotesCloudDataSource(),
                quoteCloudToCacheMapper,
                provideDao.provide(QuotesDao::class.java)
            )
        }
    }
}
