package com.yuriisurzhykov.goaldestinator.quotes

import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideConverterFactory
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideOkHttpClientBuilder
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideRetrofitBuilder
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuoteCreateService
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class QuotesServiceCallTest {

    @Test
    @Ignore("Call only locally through Android Studio")
    fun `test api call with success`(): Unit = runBlocking {
        val provideOkHttpClientBuilder = ProvideOkHttpClientBuilder.Base()
        val provideConverterFactory = ProvideConverterFactory.Gson(ProvideGson.Base().provideGson())
        val provideRetrofit =
            ProvideRetrofitBuilder.Base(provideOkHttpClientBuilder, provideConverterFactory)
        val service = QuoteCreateService.Base(provideRetrofit).create(QuotesService::class.java)
        val response = service.quotes("motivational")
        assertEquals(true, response.isSuccessful)
    }
}
