package com.yuriisurzhykov.goaldestinator.core.data.cache

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

interface ProvideDatabase {

    fun <T : RoomDatabase> provide(clazz: Class<T>): T

    abstract class Abstract(
        private val context: Context,
        private val databaseName: String
    ) : ProvideDatabase {

        override fun <T : RoomDatabase> provide(clazz: Class<T>): T {
            return Room
                .databaseBuilder(context.applicationContext, clazz, databaseName)
                .build()
        }
    }
}
