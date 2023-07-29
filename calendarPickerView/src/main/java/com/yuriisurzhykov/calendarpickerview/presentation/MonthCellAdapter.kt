package com.yuriisurzhykov.calendarpickerview.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuriisurzhykov.calendarpickerview.R
import com.yuriisurzhykov.calendarpickerview.data.days.MonthDayCell

class MonthCellAdapter :
    ListAdapter<MonthDayCell, MonthCellAdapter.ViewHolder>(MonthCellDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView by lazy(LazyThreadSafetyMode.NONE) {
            itemView.findViewById(android.R.id.text1)
        }

        fun bind(item: MonthDayCell) {
            item.applyToView(textView)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.layout_month_cell, p0, false)
        )
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(getItem(p1))
    }
}