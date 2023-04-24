package com.yuriisurzhykov.goaldestinator.quotes

import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.StringProvider
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuoteCache
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesCacheDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDao
import com.yuriisurzhykov.goaldestinator.quotes.domain.SamplesStringProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class QuotesCacheDataSourceTest {

    @Test
    fun `dao data source test empty`(): Unit = runBlocking {
        val dao = TestQuotesDao(true)
        val dataSource = QuotesCacheDataSource.Dao(dao)
        val expected = dao.emptyList
        assertEquals(expected, dataSource.quotes())
    }

    @Test
    fun `dao data source test not empty`(): Unit = runBlocking {
        val dao = TestQuotesDao(false)
        val dataSource = QuotesCacheDataSource.Dao(dao)
        val expected = dao.dataList
        assertEquals(expected, dataSource.quotes())
    }

    @Test
    fun `local samples fetch not empty`(): Unit = runBlocking {
        val stringProvider = TestLocalSamplesStringProvider()
        val serialization = Serialization.Base(ProvideGson.Base())
        val dataSource = QuotesCacheDataSource.LocalSamples(stringProvider, serialization)
        val expected = listOf(
            QuoteCache.Base(
                id = "XtZMaD2s28",
                author = "Thomas Edison",
                content = "If we all did the things we are capable of doing, " +
                        "we would literally astound ourselves.",
                lastModified = "2023-04-14"
            )
        )
        assertEquals(expected, dataSource.quotes())
    }

    @Test
    fun `local samples fetch empty string`(): Unit = runBlocking {
        val stringProvider = object : SamplesStringProvider {
            override fun provide() = ""
        }
        val serialization = Serialization.Base(ProvideGson.Base())
        val dataSource = QuotesCacheDataSource.LocalSamples(stringProvider, serialization)
        val expected = emptyList<QuoteCache.Base>()
        assertEquals(expected, dataSource.quotes())
    }

    @Test
    fun `data source with alternative all sources with empty data`(): Unit = runBlocking {
        val dao = TestQuotesDao(true)
        val stringProvider = object : SamplesStringProvider {
            override fun provide() = ""
        }
        val serialization = Serialization.Base(ProvideGson.Base())
        val localStorage = QuotesCacheDataSource.LocalSamples(stringProvider, serialization)
        val dataSource = QuotesCacheDataSource
            .WithAlternative(QuotesCacheDataSource.Dao(dao), localStorage)
        assertEquals(emptyList<QuoteCache.Base>(), dataSource.quotes())
    }

    @Test
    fun `data source with alternative all sources with only local data`(): Unit = runBlocking {
        val dao = TestQuotesDao(true)
        val stringProvider = TestLocalSamplesStringProvider()
        val serialization = Serialization.Base(ProvideGson.Base())
        val localStorage = QuotesCacheDataSource.LocalSamples(stringProvider, serialization)
        val dataSource =
            QuotesCacheDataSource.WithAlternative(QuotesCacheDataSource.Dao(dao), localStorage)
        val expected = listOf(
            QuoteCache.Base(
                id = "XtZMaD2s28",
                author = "Thomas Edison",
                content = "If we all did the things we are capable of doing, " +
                        "we would literally astound ourselves.",
                lastModified = "2023-04-14"
            )
        )
        assertEquals(expected, dataSource.quotes())
    }



    @Test
    fun `data source with alternative all sources with only cache data`(): Unit = runBlocking {
        val dao = TestQuotesDao(false)
        val stringProvider = TestLocalSamplesStringProvider()
        val serialization = Serialization.Base(ProvideGson.Base())
        val localStorage = QuotesCacheDataSource.LocalSamples(stringProvider, serialization)
        val dataSource = QuotesCacheDataSource
                .WithAlternative(QuotesCacheDataSource.Dao(dao), localStorage)
        val expected = dao.dataList
        assertEquals(expected, dataSource.quotes())
    }
}

private class TestQuotesDao(
    private val isEmpty: Boolean
) : QuotesDao {

    val emptyList = listOf<QuoteCache.Base>()
    val dataList = mutableListOf(
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

    override suspend fun quotes(): List<QuoteCache.Base> =
        if (isEmpty) emptyList else dataList

    override suspend fun insert(entity: QuoteCache.Base) {
        dataList.add(entity)
    }

    override suspend fun delete(entity: QuoteCache.Base) {
        dataList.remove(entity)
    }
}

private class TestLocalSamplesStringProvider : SamplesStringProvider {
    override fun provide() = "[{\n" +
            "\"_id\":\"XtZMaD2s28\",\n" +
            "\"author\":\"Thomas Edison\",\n" +
            "\"content\":\"If we all did the things we are capable of doing, " +
            "we would literally astound ourselves.\",\n" +
            "\"dateModified\":\"2023-04-14\"\n" +
            "}]"
}
