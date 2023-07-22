package com.yuriisurzhykov.goaldestinator

import android.os.Bundle
import android.widget.CalendarView
import androidx.fragment.app.FragmentActivity
import java.util.*

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calendarView = findViewById<CalendarView>(R.id.calendar)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
//        startActivity(Intent(this, QuoteActivity::class.java))
    }
}
