package com.yuriisurzhykov.calendarpickerview.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.yuriisurzhykov.calendarpickerview.R
import com.yuriisurzhykov.calendarpickerview.sl.CalendarDependenciesProvider
import java.util.*

class CalendarDatePickerView : ConstraintLayout {

    private val weekNamesSource = CalendarDependenciesProvider.weekDaysNames()
    private val monthsNamesSource = CalendarDependenciesProvider.monthsNames()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_calendar_picker_view, this, true)
        val weekNamesRoot = findViewById<ViewGroup>(R.id.week_names)
        weekNamesSource.getWeekDayNames().forEach { weekDayName ->
            val textView =
                inflater.inflate(R.layout.layout_week_name, weekNamesRoot, true) as TextView
            textView.text = weekDayName
        }
        val monthsSpinner = findViewById<Spinner>(R.id.month_select_spinner)
        val monthAdapter = MonthsArrayAdapter(monthsNamesSource.listOfMonths(), context)
        monthsSpinner.adapter = monthAdapter
        monthAdapter.addAll(monthsNamesSource.listOfMonths(Date().year))

        val yearsSpinner = findViewById<Spinner>(R.id.year_select_spinner)
    }
}