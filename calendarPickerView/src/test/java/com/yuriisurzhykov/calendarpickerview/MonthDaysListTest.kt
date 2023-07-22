package com.yuriisurzhykov.calendarpickerview

import com.yuriisurzhykov.calendarpickerview.data.days.MonthDayCell
import com.yuriisurzhykov.calendarpickerview.data.days.MonthDaysList
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MonthDaysListTest {

    @Test
    fun `test list of days in month`() {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY)
        calendar.set(Calendar.YEAR, 2023)

        val testClass = MonthDaysList.Base(calendar)

        val expectedList = listOf(
            listOf(
                MonthDayCell.PreviousMonthDay(29),
                MonthDayCell.PreviousMonthDay(30),
                MonthDayCell.PreviousMonthDay(31),
                MonthDayCell.Day(1),
                MonthDayCell.Day(2),
                MonthDayCell.Day(3),
                MonthDayCell.Day(4)
            ),
            listOf(
                MonthDayCell.Day(5),
                MonthDayCell.Day(6),
                MonthDayCell.Day(7),
                MonthDayCell.Day(8),
                MonthDayCell.Day(9),
                MonthDayCell.Day(10),
                MonthDayCell.Day(11)
            ),
            listOf(
                MonthDayCell.Day(12),
                MonthDayCell.Day(13),
                MonthDayCell.Day(14),
                MonthDayCell.Day(15),
                MonthDayCell.Day(16),
                MonthDayCell.Day(17),
                MonthDayCell.Day(18)
            ),
            listOf(
                MonthDayCell.Day(19),
                MonthDayCell.Day(20),
                MonthDayCell.Day(21),
                MonthDayCell.Day(22),
                MonthDayCell.Day(23),
                MonthDayCell.Day(24),
                MonthDayCell.Day(25)
            ),
            listOf(
                MonthDayCell.Day(26),
                MonthDayCell.Day(27),
                MonthDayCell.Day(28),
                MonthDayCell.NextMonthDay(1),
                MonthDayCell.NextMonthDay(2),
                MonthDayCell.NextMonthDay(3),
                MonthDayCell.NextMonthDay(4)
            )
        )

        val actualList = testClass.listOfWeeks()
        assertEquals(expectedList, actualList)
    }
}