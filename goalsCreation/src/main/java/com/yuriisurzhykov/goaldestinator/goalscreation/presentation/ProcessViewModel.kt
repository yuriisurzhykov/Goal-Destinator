package com.yuriisurzhykov.goaldestinator.goalscreation.presentation

import androidx.lifecycle.ViewModel

interface ProcessViewModel {

    fun <T : ProcessStepUi.StepParam> applyStepParam(params: T)
    fun onInitStep(stepUi: ProcessStepUi)

    abstract class Abstract(
        private val steps: LinkedHashSet<ProcessStepUi>
    ) : ViewModel(), ProcessViewModel {

        private var currentStep: ProcessStepUi? = null

        override fun <T : ProcessStepUi.StepParam> applyStepParam(params: T) {
            currentStep?.
        }
    }
}
