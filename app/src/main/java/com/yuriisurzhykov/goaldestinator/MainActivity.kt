package com.yuriisurzhykov.goaldestinator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, QuoteActivity::class.java))
    }
}
