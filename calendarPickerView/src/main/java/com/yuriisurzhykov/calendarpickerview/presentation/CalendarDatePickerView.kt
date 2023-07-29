package com.yuriisurzhykov.calendarpickerview.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuriisurzhykov.calendarpickerview.R
import com.yuriisurzhykov.calendarpickerview.data.months.MonthData
import com.yuriisurzhykov.calendarpickerview.sl.CalendarDependenciesProvider
import java.util.*

class CalendarDatePickerView : ConstraintLayout {

    fun interface OnDateChangeListener {
        fun onChanged(view: View, year: Int, month: Int, dayOfMonth: Int)
    }

    private val weekNamesSource = CalendarDependenciesProvider.weekDaysNames()
    private val monthsNamesSource = CalendarDependenciesProvider.monthsNames()
    private val yearsSource = CalendarDependenciesProvider.yearsSource()
    private val monthDaysSource = CalendarDependenciesProvider.monthDaysSource()

    private var onDateChangeListener: OnDateChangeListener? = null
    private var selectedDay: Int = Date().day

    private val yearSpinner: Spinner
    private val monthSpinner: Spinner
    private val monthRecycler: RecyclerView

    private val monthsCellsAdapter: MonthCellAdapter

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
            val rootView = inflater.inflate(R.layout.layout_month_cell, weekNamesRoot, false)
            val textView = rootView.findViewById<TextView>(android.R.id.text1)
            textView.text = weekDayName
            weekNamesRoot.addView(rootView)
        }
        monthSpinner = findViewById(R.id.month_select_spinner)
        val monthAdapter = MonthsArrayAdapter(monthsNamesSource.listOfMonths(Date().year), context)
        monthSpinner.adapter = monthAdapter

        yearSpinner = findViewById(R.id.year_select_spinner)
        val yearAdapter = YearArrayAdapter(context, yearsSource.listOfYears())
        yearSpinner.adapter = yearAdapter
        yearSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val monthPreSelection =
                    (monthSpinner.selectedItem as MonthData).map(MonthData.Mapper.MonthValue())
                monthAdapter.clear()
                val listOfMonths = monthsNamesSource.listOfMonths(yearAdapter.getItem(position)!!)
                monthAdapter.addAll(listOfMonths)
                val newFirstMonthValue = listOfMonths.first().map(MonthData.Mapper.MonthValue())
                if (monthPreSelection <= newFirstMonthValue) {
                    monthSpinner.setSelection(0)
                } else {
                    if (newFirstMonthValue == 1) {
                        monthSpinner.setSelection(monthPreSelection - 1)
                    } else {
                        monthSpinner.setSelection(12 - listOfMonths.size - monthPreSelection)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                monthAdapter.clear()
                monthAdapter.addAll(monthsNamesSource.listOfMonths(Date().year))
            }
        }

        monthRecycler = findViewById(R.id.dates_list)
        monthRecycler.layoutManager =
            GridLayoutManager(context, 7, RecyclerView.VERTICAL, false)
        monthsCellsAdapter = MonthCellAdapter()
        monthRecycler.adapter = monthsCellsAdapter
        monthsCellsAdapter.submitList(monthDaysSource.listOfWeeks())
    }

    fun setOnDateChangeListener(listener: OnDateChangeListener) {
        onDateChangeListener = listener
        triggerListener()
    }

    private fun triggerListener() {
        val year = yearSpinner.selectedItem as Int
        val month = monthSpinner.selectedItem as MonthData
        onDateChangeListener?.onChanged(
            this,
            year,
            month.map(MonthData.Mapper.MonthValue()),
            selectedDay
        )
    }
}