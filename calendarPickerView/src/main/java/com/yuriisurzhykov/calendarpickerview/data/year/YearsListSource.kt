package com.yuriisurzhykov.calendarpickerview.data.year

import java.util.Calendar

interface YearsListSource {

    fun listOfYears(): List<Int>

    class Base(
        private val calendar: Calendar,
        private val maximumShift: Int
    ) : YearsListSource {

        override fun listOfYears(): List<Int> {
            val actualResultList = mutableListOf<Int>()
            val currentYear = calendar.get(Calendar.YEAR)
            for (i in 0 .. maximumShift) {
                actualResultList.add(currentYear + i)
            }
            return actualResultList
        }
    }
}