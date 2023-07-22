package com.yuriisurzhykov.calendarpickerview

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.util.*

class WeekDaysNamesTest {

    @Test
    fun `test return list from monday through sunday`() {
        val locale = Locale.US
        val timeZone = TimeZone.getDefault()
        val formatter = CalendarFormat.ShortDayOfWeek(locale)

        val testClass = WeekDaysNames.Base(timeZone, locale, formatter)

        val expected = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val actual = testClass.getWeekDayNames()
        assertEquals(expected, actual)
    }
}