package com.yuriisurzhykov.calendarpickerview.sl

import com.yuriisurzhykov.calendarpickerview.data.CalendarFormat
import com.yuriisurzhykov.calendarpickerview.data.WeekDaysNames
import com.yuriisurzhykov.calendarpickerview.data.days.MonthDayCell
import com.yuriisurzhykov.calendarpickerview.data.days.MonthDaysList
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

    fun monthDaysSource(calendar: Calendar = calendar()): MonthDaysList {
        return MonthDaysList.Base(calendar())
    }

    fun monthDaysSource(year: Int, month: Int): MonthDaysList {
        val calendar = calendar()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        return MonthDaysList.Base(calendar)
    }
}