package com.yuriisurzhykov.goaldestinator.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yuriisurzhykov.goaldestinator.core.R

abstract class AbstractFragmentActivity : AppCompatActivity() {

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
