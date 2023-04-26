package com.yuriisurzhykov.goaldestinator.quotes.domain

import android.widget.TextView

interface Quote {

    interface Mapper<T : Any> {

        fun map(author: String, content: String): T

        class ApplyToView(
            private val view: TextView
        ) : Mapper<Unit> {
            override fun map(author: String, content: String) {
                view.text = content
            }
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val author: String,
        private val content: String
    ) : Quote {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(author, content)
    }
}
