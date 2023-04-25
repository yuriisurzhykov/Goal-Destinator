package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import com.google.gson.annotations.SerializedName
import com.yuriisurzhykov.goaldestinator.core.data.cloud.CloudModel

interface QuotesList : CloudModel {

    interface Mapper<T : Any> {
        fun map(
            count: Int,
            totalCount: Int,
            page: Int,
            totalPages: Int,
            lastItemIndex: Int,
            results: List<Quote.Base>
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("count")
        private val count: Int,
        @SerializedName("totalCount")
        private val totalCount: Int,
        @SerializedName("page")
        private val page: Int,
        @SerializedName("totalPages")
        private val totalPages: Int,
        @SerializedName("lastItemIndex")
        private val lastItemIndex: Int,
        @SerializedName("results")
        private val results: List<Quote.Base>
    ) : QuotesList {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(count, totalCount, page, totalPages, lastItemIndex, results)
        }
    }
}