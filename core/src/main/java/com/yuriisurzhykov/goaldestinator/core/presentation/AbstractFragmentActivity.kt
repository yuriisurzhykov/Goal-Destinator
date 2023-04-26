package com.yuriisurzhykov.goaldestinator.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.yuriisurzhykov.goaldestinator.core.R

abstract class AbstractFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singlepane)
    }

    protected fun openFragment(
        fragment: Fragment,
        tag: String = fragment.toString(),
        addToBackStack: Boolean = false
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_container, fragment, tag)
        if (addToBackStack) transaction.addToBackStack(tag)
        transaction.commit()
    }
}
