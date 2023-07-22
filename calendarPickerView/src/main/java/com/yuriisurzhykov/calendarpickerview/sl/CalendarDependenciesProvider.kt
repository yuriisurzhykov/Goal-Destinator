package com.yuriisurzhykov.calendarpickerview.sl

import com.yuriisurzhykov.calendarpickerview.data.CalendarFormat
import com.yuriisurzhykov.calendarpickerview.data.WeekDaysNames
import com.yuriisurzhykov.calendarpickerview.data.months.MonthNamesSource
import java.util.*

object CalendarDependenciesProvider {

    private fun timeZone() = TimeZone.getDefault()

    private fun locale() = Locale.getDefault()

    private fun calendar() = Calendar.getInstance(locale())

    fun weekDaysNames(): WeekDaysNames {
        return WeekDaysNames.Base(timeZone(), locale(), CalendarFormat.MonthShortName(locale()))
    }

    fun monthsNames(): MonthNamesSource {
        return MonthNamesSource.Base(CalendarFormat.MonthName(locale()), calendar())
    }
}