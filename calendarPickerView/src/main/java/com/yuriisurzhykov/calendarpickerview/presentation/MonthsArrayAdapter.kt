package com.yuriisurzhykov.calendarpickerview.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.yuriisurzhykov.calendarpickerview.data.months.MonthData

class MonthsArrayAdapter(
    items: List<MonthData>,
    context: Context
) : ArrayAdapter<MonthData>(context, android.R.layout.simple_list_item_1, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent).apply {
            getItem(position)?.map(
                MonthData.Mapper.ApplyTextView(
                    this.findViewById(android.R.id.text1)
                )
            )
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent).apply {
            getItem(position)?.map(
                MonthData.Mapper.ApplyTextView(
                    this.findViewById(android.R.id.text1)
                )
            )
        }
    }
}