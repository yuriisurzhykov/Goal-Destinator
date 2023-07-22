package com.yuriisurzhykov.calendarpickerview.data.days

import java.util.Calendar

interface MonthDaysList {

    fun listOfWeeks(): List<List<MonthDayCell>>

    class Base(
        private val calendar: Calendar
    ) : MonthDaysList {

        override fun listOfWeeks(): List<List<MonthDayCell>> {
            val resultList = mutableListOf<List<MonthDayCell>>()
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            val currentMonth = calendar.get(Calendar.MONTH)
            val dayPositionInWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val amountOfWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
            val maximumDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            val firstWeekList = mutableListOf<MonthDayCell>()
            calendar.add(Calendar.DAY_OF_MONTH, 1 - dayPositionInWeek)
            for (shift in 1..7) {
                val monthDay = calendar.get(Calendar.DAY_OF_MONTH)
                if (calendar.get(Calendar.MONTH) != currentMonth) {
                    firstWeekList.add(MonthDayCell.PreviousMonthDay(monthDay))
                } else {
                    firstWeekList.add(MonthDayCell.Day(monthDay))
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            resultList.add(firstWeekList)
            for (week in 1 until amountOfWeeks) {
                val weekList = mutableListOf<MonthDayCell>()
                for (shift in 1..7) {
                    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                    val month = calendar.get(Calendar.MONTH)
                    if (dayOfMonth <= maximumDays && month == currentMonth) {
                        weekList.add(MonthDayCell.Day(dayOfMonth))
                    } else {
                        weekList.add(MonthDayCell.NextMonthDay(dayOfMonth))
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }
                resultList.add(weekList)
            }
            return resultList
        }
    }
}