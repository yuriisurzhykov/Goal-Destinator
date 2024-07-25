package com.yuriisurzhykov.goaldestinator.core.presentation.view

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class CenteredToolbar : Toolbar {

    private var centeredTitleTextView: TextView? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setTitle(resId: Int) {
        val s = resources.getString(resId)
        title = s
    }

    override fun setTitle(title: CharSequence?) {
        getCenteredTitleTextView().text = title
    }

    override fun getTitle(): CharSequence {
        return getCenteredTitleTextView().text.toString()
    }

    fun setTypeface(font: Typeface) {
        getCenteredTitleTextView().typeface = font
    }

    private fun getCenteredTitleTextView(): TextView {
        if (centeredTitleTextView == null) {
            val centeredTitleTextView = TextView(context)
            centeredTitleTextView.setSingleLine()
            centeredTitleTextView.ellipsize = TextUtils.TruncateAt.END
            centeredTitleTextView.gravity = Gravity.CENTER
            centeredTitleTextView.setTextAppearance(
                com.google.android.material.R.style.TextAppearance_Material3_ActionBar_Title
            )

            val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            lp.gravity = Gravity.CENTER
            centeredTitleTextView.layoutParams = lp
            addView(centeredTitleTextView)
            this.centeredTitleTextView = centeredTitleTextView
        }
        return centeredTitleTextView!!
    }
}
