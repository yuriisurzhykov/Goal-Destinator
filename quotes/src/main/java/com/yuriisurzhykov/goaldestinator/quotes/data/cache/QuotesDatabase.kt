package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuoteCache.Base::class],
    version = 1,
    exportSchema = true
)
abstract class QuotesDatabase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao
}
