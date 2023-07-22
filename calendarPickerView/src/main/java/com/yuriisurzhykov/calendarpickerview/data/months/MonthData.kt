package com.yuriisurzhykov.calendarpickerview.data.months

import android.widget.TextView
import java.lang.ref.SoftReference

interface MonthData {

    interface Mapper<T : Any> {
        fun map(name: String, order: Int): T

        class ApplyTextView(
            textView: TextView
        ) : Mapper<Unit> {

            private val textViewRef = SoftReference(textView)

            override fun map(name: String, order: Int) {
                textViewRef.get()?.text = name
            }
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val name: String,
        private val order: Int
    ) : MonthData {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(name, order)
        }
    }
}