package com.yuriisurzhykov.goaldestinator.quotes.domain

import android.content.Context
import com.yuriisurzhykov.goaldestinator.core.data.StringProvider

interface SamplesStringProvider : StringProvider {

    class Base(
        private val context: Context
    ) : SamplesStringProvider {
        override fun provide(): String {
            return context.assets.open("samples.json").use {
                return@use it.readBytes().toString(Charsets.UTF_8)
            }
        }
    }
}
