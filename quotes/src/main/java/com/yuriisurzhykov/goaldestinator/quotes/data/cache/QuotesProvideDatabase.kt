package com.yuriisurzhykov.goaldestinator.quotes.data.cache

import android.content.Context
import com.yuriisurzhykov.goaldestinator.core.data.cache.ProvideDatabase

class QuotesProvideDatabase(
    context: Context
) : ProvideDatabase.Abstract(
    context,
    "quotes.db"
)
