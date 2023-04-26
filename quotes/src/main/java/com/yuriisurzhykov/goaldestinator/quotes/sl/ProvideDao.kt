package com.yuriisurzhykov.goaldestinator.quotes.sl

import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDao
import com.yuriisurzhykov.goaldestinator.quotes.data.cache.QuotesDatabase

interface ProvideDao {

    fun <T : Any> provide(clazz: Class<T>): T

    class Base(
        private val provideDatabase: QuotesDatabase,
        private val error: Error = Error()
    ) : ProvideDao {

        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> provide(clazz: Class<T>): T = when (clazz) {
            QuotesDao::class.java -> provideDatabase.quotesDao() as T
            else -> error.provide(clazz)
        }
    }

    class Error : ProvideDao {
        override fun <T : Any> provide(clazz: Class<T>): T =
            throw IllegalStateException("There is not dao for class $clazz")
    }
}
