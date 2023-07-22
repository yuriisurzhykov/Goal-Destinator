package com.yuriisurzhykov.calendarpickerview.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.yuriisurzhykov.calendarpickerview.data.months.MonthData

class MonthsArrayAdapter(
    items: List<MonthData>,
    context: Context
) : ArrayAdapter<MonthData>(context, android.R.layout.simple_list_item_1, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val superView = super.getView(position, convertView, parent)
        getItem(position)?.map(
            MonthData.Mapper.ApplyTextView(
                superView.findViewById(android.R.id.text1)
            )
        )
        return superView
    }
}