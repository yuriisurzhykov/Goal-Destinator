package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {

    @GET("random")
    suspend fun quotes(@Query("tags") tags: String): Response<QuotesList.Base>
}
