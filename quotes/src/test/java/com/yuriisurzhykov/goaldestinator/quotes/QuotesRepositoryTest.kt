package com.yuriisurzhykov.goaldestinator.quotes

import com.yuriisurzhykov.goaldestinator.quotes.data.QuoteCloudListToCacheListMapper
import com.yuriisurzhykov.goaldestinator.quotes.data.QuoteCloudToCacheMapper
import com.yuriisurzhykov.goaldestinator.quotes.data.QuotesRepository
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesCacheDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDao
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.Quote
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesCloudDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class QuotesRepositoryTest {

    @Test
    fun `test repository response when cloud gives exception`(): Unit = runBlocking {
        val cacheSource = TestQuoteCacheDataSource()
        val cloudSource = TestQuoteCloudDataSource()
        val mapper = QuoteCloudListToCacheListMapper.Base(QuoteCloudToCacheMapper.Base())
        val dao = TestQuotesRepositoryDao()
        val repository = QuotesRepository.Base(cacheSource, cloudSource, mapper, dao)
        cloudSource.exception = java.net.UnknownHostException()
        val expected = cacheSource.dataList
        assertEquals(expected, repository.quotes())
    }

    @Test
    fun `test repository response when cloud gives result`(): Unit = runBlocking {
        val cacheSource = TestQuoteCacheDataSource()
        val cloudSource = TestQuoteCloudDataSource()
        val mapper = QuoteCloudListToCacheListMapper.Base(QuoteCloudToCacheMapper.Base())
        val dao = TestQuotesRepositoryDao()
        val repository = QuotesRepository.Base(cacheSource, cloudSource, mapper, dao)
        val expected = cloudSource.dataItem.map(mapper)
        assertEquals(expected, repository.quotes())
    }
}

private class TestQuoteCacheDataSource : QuotesCacheDataSource {

    val dataList = listOf(
        QuoteCache.Base(
            "3ad11",
            "Yurii1",
            "Content 1",
            "11.11.1111"
        ),
        QuoteCache.Base(
            "3ad22",
            "Yurii2",
            "Content 2",
            "11.11.2222"
        )
    )

    override suspend fun quotes(): List<QuoteCache.Base> = dataList
}

private class TestQuoteCloudDataSource : QuotesCloudDataSource {

    var exception: Exception? = null

    val dataItem = QuotesList.Base(
        1,
        1,
        1,
        1,
        1,
        listOf(
            Quote.Base(
                "asd223",
                "Content 1",
                "Author 1",
                "asdlk10239",
                112,
                listOf("motivational"),
                "asdas2131"
            )
        )
    )

    override suspend fun data(): QuotesList.Base {
        if (exception == null) return dataItem
        throw exception!!
    }
}

private class TestQuotesRepositoryDao : QuotesDao {

    val items = mutableListOf<QuoteCache.Base>()

    override suspend fun quotes(): List<QuoteCache.Base> = items

    override suspend fun insert(entity: QuoteCache.Base) {
        items.add(entity)
    }

    override suspend fun insertAll(entity: List<QuoteCache.Base>) {
        items.addAll(entity)
    }

    override suspend fun delete(entity: QuoteCache.Base) {
        items.remove(entity)
    }
}
