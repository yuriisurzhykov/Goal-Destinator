package com.yuriisurzhykov.calendarpickerview.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class YearArrayAdapter(
    context: Context,
    list: List<Int>
) : ArrayAdapter<Int>(context, android.R.layout.simple_list_item_1, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent).apply {
            findViewById<TextView>(android.R.id.text1).text = getItem(position).toString()
        }
    }
}