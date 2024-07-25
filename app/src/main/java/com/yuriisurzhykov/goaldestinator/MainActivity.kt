package com.yuriisurzhykov.goaldestinator

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.yuriisurzhykov.goaldestinator.goals.creation.CreateGoalActivity
import com.yuriisurzhykov.goaldestinator.quotes.presentation.QuoteActivity

class MainActivity : FragmentActivity() {

    private val quoteActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            startActivity(Intent(this, CreateGoalActivity::class.java))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quoteActivityLauncher.launch(Intent(this, QuoteActivity::class.java))
    }
}
