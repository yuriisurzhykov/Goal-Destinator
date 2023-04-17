package com.yuriisurzhykov.goaldestinator.core.data.cache

import android.content.Context
import android.content.SharedPreferences

interface ProvideSharedPreferences {

    fun sharedPreferences(): SharedPreferences

    abstract class Abstract(
        private val context: Context,
        private val name: String,
        private val visibility: Int = Context.MODE_PRIVATE
    ) : ProvideSharedPreferences {

        override fun sharedPreferences(): SharedPreferences =
            context.getSharedPreferences(name, visibility)
    }

    class Debug(context: Context) : Abstract(context, "Debug")

    class Release(context: Context) : Abstract(context, "Release")
}
