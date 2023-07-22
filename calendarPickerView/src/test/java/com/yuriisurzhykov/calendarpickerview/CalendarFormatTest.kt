package com.yuriisurzhykov.calendarpickerview

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar
import java.util.Locale

class CalendarFormatTest {

    @Test
    fun `test format weekday name for USA monday`() {
        val locale = Locale.US
        val testClass = CalendarFormat.DayOfWeek(locale)
        val date = Calendar.getInstance(locale).apply {
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        }.time
        val expected = "Monday"
        val actual = testClass.format(date)
        assertEquals(expected, actual)
    }

    @Test
    fun `test format month name for USA monday`() {
        val locale = Locale.US
        val testClass = CalendarFormat.MonthName(locale)
        val date = Calendar.getInstance(locale).apply {
            set(Calendar.MONTH, Calendar.DECEMBER)
        }.time
        val expected = "Dec"
        val actual = testClass.format(date)
        assertEquals(expected, actual)
    }
}