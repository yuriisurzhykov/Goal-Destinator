package com.yuriisurzhykov.calendarpickerview.presentation

import androidx.recyclerview.widget.DiffUtil
import com.yuriisurzhykov.calendarpickerview.data.days.MonthDayCell

class MonthCellDiffCallback : DiffUtil.ItemCallback<MonthDayCell>() {
    override fun areItemsTheSame(p0: MonthDayCell, p1: MonthDayCell): Boolean {
        return p0 == p1
    }

    override fun areContentsTheSame(p0: MonthDayCell, p1: MonthDayCell): Boolean {
        return p0 == p1
    }
}