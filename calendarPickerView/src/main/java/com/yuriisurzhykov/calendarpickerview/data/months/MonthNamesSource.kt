package com.yuriisurzhykov.calendarpickerview.data.months

import com.yuriisurzhykov.calendarpickerview.data.CalendarFormat
import java.util.*

interface MonthNamesSource {

    fun listOfMonths(): List<MonthData>
    fun listOfMonths(year: Int): List<MonthData>

    class Base(
        private val formatter: CalendarFormat,
        private val calendar: Calendar
    ) : MonthNamesSource {

        override fun listOfMonths(): List<MonthData> {
            val calendarCopy = Calendar.getInstance()
            calendarCopy.time = calendar.time
            val actualResultList = mutableListOf<MonthData>()
            val startMonth = calendarCopy.getActualMinimum(Calendar.MONTH)
            val endMonth = calendarCopy.getActualMaximum(Calendar.MONTH)
            for (month in startMonth..endMonth) {
                calendarCopy.set(Calendar.MONTH, month)
                val monthName = formatter.format(calendarCopy.time)
                actualResultList.add(MonthData.Base(monthName, month + 1))
            }
            return actualResultList
        }

        override fun listOfMonths(year: Int): List<MonthData> {
            val calendarCopy = Calendar.getInstance()
            calendarCopy.time = calendar.time
            val currentMonth = calendarCopy.get(Calendar.MONTH)
            val currentYear = calendarCopy.get(Calendar.YEAR)
            val fullList = listOfMonths()
            return if (currentYear < year) {
                fullList
            } else {
                fullList.drop(currentMonth)
            }
        }
    }
}