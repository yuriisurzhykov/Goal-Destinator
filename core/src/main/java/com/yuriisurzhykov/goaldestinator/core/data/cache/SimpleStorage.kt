package com.yuriisurzhykov.goaldestinator.core.data.cache

interface SimpleStorage : StringStorage {

    class Base(
        provideSharedPreferences: ProvideSharedPreferences
    ) : SimpleStorage {

        private val sharedPreferences by lazy { provideSharedPreferences.sharedPreferences() }

        override fun save(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }

        override fun read(key: String, default: String): String {
            return sharedPreferences.getString(key, default) ?: default
        }
    }
}
