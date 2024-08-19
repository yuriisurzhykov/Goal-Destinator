package com.yuriisurzhykov.goaldestinator.goals.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update

class GoalsViewModel : ViewModel() {

    companion object {
        // emptyList<Goal>()
        private val testGoals = mutableListOf(
            Goal(
                "First goal in the whole world!",
                "The description of goal that has not finished yet",
                false
            ),
            Goal(
                "First finished goal!",
                "The description of goal that has been finished",
                true
            )
        )
    }

    private val goalsList = MutableStateFlow(testGoals)
    val goals = goalsList.asStateFlow()

    fun onGoalCheckedChange(goal: Goal, checked: Boolean) {
        goalsList.update {
            testGoals.apply {
                remove(goal)
                add(goal.copy(completed = checked))
            }
        }
    }
}