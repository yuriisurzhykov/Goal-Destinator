package com.yuriisurzhykov.goaldestinator.quotes.presentation

import android.os.Bundle
import com.yuriisurzhykov.goaldestinator.core.presentation.AbstractFragmentActivity

class QuoteActivity : AbstractFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(QuoteFragment())
    }
}
