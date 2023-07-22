package com.yuriisurzhykov.calendarpickerview.sl

import com.yuriisurzhykov.calendarpickerview.data.CalendarFormat
import com.yuriisurzhykov.calendarpickerview.data.WeekDaysNames
import com.yuriisurzhykov.calendarpickerview.data.months.MonthNamesSource
import com.yuriisurzhykov.calendarpickerview.data.year.YearsListSource
import java.util.*

object CalendarDependenciesProvider {

    private fun timeZone() = TimeZone.getDefault()

    private fun locale() = Locale.getDefault()

    private fun calendar() = Calendar.getInstance(locale())

    fun weekDaysNames(): WeekDaysNames {
        return WeekDaysNames.Base(timeZone(), locale(), CalendarFormat.ShortDayOfWeek(locale()))
    }

    fun monthsNames(): MonthNamesSource {
        return MonthNamesSource.Base(CalendarFormat.MonthShortName(locale()), calendar())
    }

    fun yearsSource(): YearsListSource {
        return YearsListSource.Base(calendar(), 10)
    }
}