package com.yuriisurzhykov.calendarpickerview

import com.yuriisurzhykov.calendarpickerview.data.year.YearsListSource
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class YearsListSourceTest {

    @Test
    fun `test list for years 2023 with shift 3 years`() {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        val maxShift = 3
        val testClass = YearsListSource.Base(calendar, maxShift)
        val expectedResult = listOf(2023, 2024, 2025, 2026)
        val actualResult = testClass.listOfYears()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `test list for years 2020 with shift 10 years`() {
        val calendar = Calendar.getInstance(Locale.ENGLISH).apply {
            set(Calendar.YEAR, 2020)
        }
        val maxShift = 10
        val testClass = YearsListSource.Base(calendar, maxShift)
        val expectedResult =
            listOf(2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030)
        val actualResult = testClass.listOfYears()
        assertEquals(expectedResult, actualResult)
    }
}