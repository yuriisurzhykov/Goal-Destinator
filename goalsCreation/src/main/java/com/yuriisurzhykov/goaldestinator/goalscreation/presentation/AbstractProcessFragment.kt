package com.yuriisurzhykov.goaldestinator.goalscreation.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yuriisurzhykov.goaldestinator.goalsCreation.R

abstract class AbstractProcessFragment<VM : ProcessViewModel, S : ProcessStepUi.StepParam> :
    Fragment, ProcessStepUi {

    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    private var viewModel: VM? = null

    @StringRes
    abstract fun getTitleRes(): Int

    @StringRes
    abstract fun getSubtitleRes(): Int

    abstract fun provideViewModel(provider: ViewModelProvider): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { provideViewModel(ViewModelProvider(it)) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.onInitStep(this)
        view.findViewById<TextView>(R.id.screen_title).setText(getTitleRes())
        view.findViewById<TextView>(R.id.screen_subtitle).setText(getSubtitleRes())
    }

    override fun open(activity: ProcessActivityContract) {
        activity.openFragment(this, this.toString())
    }

    fun applyStepParam(param: S) {
        viewModel?.applyStepParam(param)
    }
}
