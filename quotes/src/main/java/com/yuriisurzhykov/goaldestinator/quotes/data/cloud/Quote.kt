package com.yuriisurzhykov.goaldestinator.quotes.data.cloud

import com.google.gson.annotations.SerializedName
import com.yuriisurzhykov.goaldestinator.core.data.cloud.CloudModel

interface Quote : CloudModel {

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
