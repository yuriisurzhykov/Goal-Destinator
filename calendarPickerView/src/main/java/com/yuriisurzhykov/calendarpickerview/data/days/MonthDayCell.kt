package com.yuriisurzhykov.calendarpickerview.data.days

interface MonthDayCell {

    class Empty : MonthDayCell

    data class Day(
        private val date: Int
    ) : MonthDayCell

    data class NextMonthDay(
        private val date: Int
    ) : MonthDayCell

    data class PreviousMonthDay(
        private val date: Int
    ) : MonthDayCell
}