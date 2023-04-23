package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

interface QuoteCache {

    interface Mapper<T : Any> {
        fun map(id: String, author: String, content: String, lastModified: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    @Entity(tableName = "Quotes")
    data class Base(
        @PrimaryKey
        private val id: String,
        private val author: String,
        private val content: String,
        private val lastModified: String
    ) : QuoteCache {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(id, author, content, lastModified)
        }
    }
}
