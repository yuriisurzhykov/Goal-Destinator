package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

interface QuoteCache {

    interface Mapper<T : Any> {
        fun map(id: String, author: String, content: String, lastModified: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    @Entity(tableName = "Quotes")
    data class Base(
        @PrimaryKey
        @SerializedName("_id")
        private val id: String,
        @SerializedName("author")
        private val author: String,
        @SerializedName("content")
        private val content: String,
        @SerializedName("dateModified")
        private val lastModified: String
    ) : QuoteCache {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(id, author, content, lastModified)
        }
    }
}
