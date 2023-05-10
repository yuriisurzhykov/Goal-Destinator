package com.yuriisurzhykov.goaldestinator.goalscreation.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yuriisurzhykov.goaldestinator.goalsCreation.R

interface ProcessStepUi : java.io.Serializable {

    interface StepParam {

        class GoalName(
            private val name: String
        ) : StepParam
    }

    fun open(activity: ProcessActivityContract)

    class GoalNameInput :
        AbstractProcessFragment<CreateGoalViewModel, StepParam.GoalName>(
            R.layout.fragment_goal_name_input
        ), ProcessStepUi {

        override fun provideViewModel(provider: ViewModelProvider) =
            provider[CreateGoalViewModel::class.java]

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val editText = view.findViewById<EditText>(R.id.goal_input)
            view.findViewById<Button>(R.id.button).setOnClickListener {
                applyStepParam(StepParam.GoalName(editText.text.toString()))
            }
        }
    }
}
