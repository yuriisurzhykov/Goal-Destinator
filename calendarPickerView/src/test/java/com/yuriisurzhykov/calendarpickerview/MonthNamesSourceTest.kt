package com.yuriisurzhykov.calendarpickerview

import com.yuriisurzhykov.calendarpickerview.data.CalendarFormat
import com.yuriisurzhykov.calendarpickerview.data.months.MonthData
import com.yuriisurzhykov.calendarpickerview.data.months.MonthNamesSource
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MonthNamesSourceTest {

    @Test
    fun `month names list from base source`() {
        val locale = Locale.US
        val formatter = CalendarFormat.MonthName(locale)
        val testClass = MonthNamesSource.Base(formatter, Calendar.getInstance(locale))
        val expectedResult = listOf(
            MonthData.Base("January", 1),
            MonthData.Base("February", 2),
            MonthData.Base("March", 3),
            MonthData.Base("April", 4),
            MonthData.Base("May", 5),
            MonthData.Base("June", 6),
            MonthData.Base("July", 7),
            MonthData.Base("August", 8),
            MonthData.Base("September", 9),
            MonthData.Base("October", 10),
            MonthData.Base("November", 11),
            MonthData.Base("December", 12),
        )
        val actualResult = testClass.listOfMonths()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test when selected current half year`() {
        val locale = Locale.US
        val calendar = Calendar.getInstance(locale).apply {
            set(Calendar.MONTH, Calendar.OCTOBER)
        }
        val formatter = CalendarFormat.MonthName(locale)
        val testClass = MonthNamesSource.Base(formatter, calendar)
        val expectedResult = listOf(
            MonthData.Base("October", 10),
            MonthData.Base("November", 11),
            MonthData.Base("December", 12),
        )
        val actualResult = testClass.listOfMonths(calendar.get(Calendar.MONTH))
        assertEquals(expectedResult, actualResult)
    }
}