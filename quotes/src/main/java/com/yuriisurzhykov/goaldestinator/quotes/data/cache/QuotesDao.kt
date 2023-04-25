package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuotesDao {

    @Query("SELECT * FROM Quotes")
    suspend fun quotes(): List<QuoteCache.Base>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: QuoteCache.Base)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<QuoteCache.Base>)

    @Delete
    suspend fun delete(entity: QuoteCache.Base)
}
