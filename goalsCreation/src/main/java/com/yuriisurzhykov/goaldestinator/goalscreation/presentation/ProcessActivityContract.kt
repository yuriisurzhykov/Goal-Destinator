package com.yuriisurzhykov.goaldestinator.goalscreation.presentation

import androidx.fragment.app.Fragment

interface ProcessActivityContract {
    fun openFragment(fragment: Fragment, tag: String)
}