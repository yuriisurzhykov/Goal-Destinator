package com.yuriisurzhykov.goaldestinator.quotes.domain

interface Quote {

    interface Mapper<T : Any> {
        fun map(author: String, content: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    class Base(
        private val author: String,
        private val content: String
    ) : Quote {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(author, content)
    }
}