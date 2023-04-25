package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import com.google.gson.annotations.SerializedName
import com.yuriisurzhykov.goaldestinator.core.data.cloud.CloudModel

interface Quote : CloudModel {

    interface Mapper<T : Any> {
        fun map(
            id: String,
            content: String,
            author: String,
            authorSlug: String,
            length: Long,
            tags: List<String>,
            dateModified: String
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("content")
        private val content: String,
        @SerializedName("author")
        private val author: String,
        @SerializedName("authorSlug")
        private val authorSlug: String,
        @SerializedName("length")
        private val length: Long,
        @SerializedName("tags")
        private val tags: List<String>,
        @SerializedName("dateModified")
        private val dateModified: String
    ) : Quote {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(id, content, author, authorSlug, length, tags, dateModified)
        }
    }
}
