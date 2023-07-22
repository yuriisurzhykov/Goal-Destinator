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
            val actualResultList = mutableListOf<MonthData>()
            val startMonth = calendar.getActualMinimum(Calendar.MONTH)
            val endMonth = calendar.getActualMaximum(Calendar.MONTH)
            for (month in startMonth..endMonth) {
                calendar.set(Calendar.MONTH, month)
                val monthName = formatter.format(calendar.time)
                actualResultList.add(MonthData.Base(monthName, month + 1))
            }
            return actualResultList
        }

        override fun listOfMonths(year: Int): List<MonthData> {
            val calendar = Calendar.getInstance()
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentYear = calendar.get(Calendar.YEAR)
            val fullList = listOfMonths()
            return if (currentYear < year) {
                fullList
            } else {
                fullList.drop(currentMonth - 1)
            }
        }
    }
}