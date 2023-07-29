package com.yuriisurzhykov.calendarpickerview.data.days

import android.widget.TextView

interface MonthDayCell {

    fun applyToView(textView: TextView)

    abstract class Abstract(
        private val date: Int
    ) : MonthDayCell {
        override fun applyToView(textView: TextView) {
            textView.text = date.toString()
        }
    }

    data class Day(
        private val date: Int
    ) : Abstract(date)

    abstract class NotCurrent(
        private val date: Int
    ) : Abstract(date) {
        override fun applyToView(textView: TextView) {
            super.applyToView(textView)
            textView.isEnabled = false
        }
    }

    data class NextMonthDay(
        private val date: Int
    ) : NotCurrent(date)

    data class PreviousMonthDay(
        private val date: Int
    ) : NotCurrent(date)
}