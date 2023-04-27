package com.yuriisurzhykov.goaldestinator.quotes

import com.yuriisurzhykov.goaldestinator.core.data.cloud.NoDataFoundException
import com.yuriisurzhykov.goaldestinator.core.data.cloud.ServiceNotAvailableException
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.Quote
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesCloudDataSource
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesList
import com.yuriisurzhykov.goaldestinator.quotes.data.cloud.QuotesService
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

class QuotesCloudDataSourceTest {

    private val quoteResponse = QuotesList.Base(
        1, 1, 1, 1, 1, listOf(
            Quote.Base(
                "112990a",
                "Content 1",
                "Author 1",
                "asjdkask192",
                10,
                listOf("motivational"),
                "12.12.12"
            )
        )
    )

    @Test
    fun `test service return success result`(): Unit = runBlocking {
        val fakeService = TestQuotesService()
        val dataSource = QuotesCloudDataSource.Motivation(fakeService)
        fakeService.response = Response.success(quoteResponse)
        val actual = dataSource.data()
        assertEquals(quoteResponse, actual)
    }

    @Test(expected = NoDataFoundException::class)
    fun `test service return no data found result`(): Unit = runBlocking {
        val fakeService = TestQuotesService()
        val dataSource = QuotesCloudDataSource.Motivation(fakeService)
        fakeService.response =
            Response.error(
                404,
                "{error:\"No data found!\"}".toResponseBody("application/json".toMediaType())
            )
        dataSource.data()
    }

    @Test(expected = ServiceNotAvailableException::class)
    fun `test service return service unavailable`(): Unit = runBlocking {
        val fakeService = TestQuotesService()
        val dataSource = QuotesCloudDataSource.Motivation(fakeService)
        fakeService.response = Response.error(
            503,
            "{error:\"Service is not available right now!\"}"
                .toResponseBody("application/json".toMediaType())
        )
        dataSource.data()
    }

    @Test(expected = UnknownHostException::class)
    fun `test service throw exception`(): Unit = runBlocking {
        val fakeService = TestQuotesService()
        val dataSource = QuotesCloudDataSource.Motivation(fakeService)
        fakeService.exception = UnknownHostException("No internet connection!")
        dataSource.data()
    }
}

private class TestQuotesService : QuotesService {

    var exception: Exception? = null
    var response: Response<QuotesList.Base>? = null

    override suspend fun quotes(tags: String): Response<QuotesList.Base> {
        return if (exception == null) response!!
        else throw exception!!
    }
}