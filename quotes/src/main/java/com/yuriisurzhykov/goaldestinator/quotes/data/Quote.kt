package com.yuriisurzhykov.goaldestinator.quotes.data

import com.google.gson.annotations.SerializedName

interface Quote {

    data class Base(
        @SerializedName("id")
        val id: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("authorSlug")
        val authorSlug: String,
        @SerializedName("length")
        val length: Long,
        @SerializedName("tags")
        val tags: List<String>
    ) : Quote
}
