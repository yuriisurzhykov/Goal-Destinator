package com.yuriisurzhykov.goaldestinator.quotes.data

import com.google.gson.annotations.SerializedName

interface Quote {

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
        private val tags: List<String>
    ) : Quote
}
