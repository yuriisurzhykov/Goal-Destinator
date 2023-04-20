package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {

    @GET("random")
    suspend fun randomQuote(@Query("tags") tags: String): Response<Quote.Base>
}
