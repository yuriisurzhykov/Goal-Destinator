package com.yuriisurzhykov.goaldestinator.goalscreation.presentation

import androidx.fragment.app.Fragment
import com.yuriisurzhykov.goaldestinator.core.presentation.AbstractFragmentActivity

class CreateGoalActivity : AbstractFragmentActivity(), ProcessActivityContract {
    override fun openFragment(fragment: Fragment, tag: String) =
        openFragment(fragment, tag, true)
}