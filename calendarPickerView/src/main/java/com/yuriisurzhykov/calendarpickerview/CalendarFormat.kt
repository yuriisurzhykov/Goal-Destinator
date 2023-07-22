package com.yuriisurzhykov.calendarpickerview

import java.text.SimpleDateFormat
import java.util.*

interface CalendarFormat {

    fun format(date: Date): String

    abstract class Abstract(
        private val dateStringFormat: String,
        private val locale: Locale
    ) : CalendarFormat {

        private val dateFormat by lazy {
            SimpleDateFormat(dateStringFormat, locale)
        }

        override fun format(date: Date): String {
            return dateFormat.format(date)
        }
    }

    class DayOfWeek(
        locale: Locale
    ) : Abstract("EEEE", locale)

    class ShortDayOfWeek(
        locale: Locale
    ) : Abstract("EEE", locale)

    class MonthName(
        locale: Locale
    ) : Abstract("MMM", locale)

    class DayOfMonth(
        locale: Locale
    ) : Abstract("D", locale)
}