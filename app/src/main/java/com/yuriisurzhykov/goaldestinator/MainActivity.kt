package com.yuriisurzhykov.goaldestinator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, QuoteActivity::class.java))
    }
}
