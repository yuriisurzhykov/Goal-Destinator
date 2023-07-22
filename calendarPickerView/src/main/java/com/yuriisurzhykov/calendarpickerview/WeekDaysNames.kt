package com.yuriisurzhykov.calendarpickerview

import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

interface WeekDaysNames {

    fun getWeekDayNames(): List<String>

    abstract class Abstract(
        private val timeZone: TimeZone,
        private val locale: Locale,
        private val formatter: CalendarFormat
    )

    class Base(
        timeZone: TimeZone,
        locale: Locale = Locale.getDefault(),
        private val formatter: CalendarFormat
    ) : WeekDaysNames {

        private val calendar = Calendar.getInstance(timeZone, locale)

        override fun getWeekDayNames(): List<String> {
            val actualList = mutableListOf<String>()
            val minimum = calendar.getActualMinimum(Calendar.DAY_OF_WEEK)
            val maximum = calendar.getActualMaximum(Calendar.DAY_OF_WEEK)
            for (i in minimum..maximum) {
                calendar.set(Calendar.DAY_OF_WEEK, i)
                actualList.add(formatter.format(calendar.time))
            }
            return actualList
        }
    }
}