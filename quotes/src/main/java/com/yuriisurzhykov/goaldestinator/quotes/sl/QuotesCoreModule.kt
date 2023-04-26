package com.yuriisurzhykov.goaldestinator.quotes.sl

import android.content.Context
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideConverterFactory
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideOkHttpClientBuilder
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideRetrofitBuilder
import com.yuriisurzhykov.goaldestinator.core.presentation.DispatcherCall
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDatabase
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesProvideDatabase
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuoteCreateService
import okhttp3.logging.HttpLoggingInterceptor

interface QuotesCoreModule {

    fun provideQuoteDependencyContainer(): QuoteDependencyContainer

    class Base(context: Context) : QuotesCoreModule {

        private val quotesDatabase by lazy {
            QuotesProvideDatabase(context).provide(QuotesDatabase::class.java)
        }
        private val loggingInterceptor by lazy {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        private val createService by lazy {
            QuoteCreateService.Base(
                ProvideRetrofitBuilder.Base(
                    ProvideOkHttpClientBuilder.Base(),
                    ProvideConverterFactory.Gson(ProvideGson.Base().provideGson())
                )
            )
        }
        private val provideDao by lazy {
            ProvideDao.Base(quotesDatabase)
        }
        private val provideRepository by lazy {
            ProvideQuotesRepository.Base(
                provideDao,
                createService,
                context
            )
        }
        private val provideUseCase by lazy {
            QuotesProvideUseCase.Base(provideRepository)
        }

        private val dispatcherCall: DispatcherCall = DispatcherCall.Base()

        override fun provideQuoteDependencyContainer(): QuoteDependencyContainer {
            return QuoteDependencyContainer.Base(
                dispatcherCall,
                provideUseCase
            )
        }
    }
}
