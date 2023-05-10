package com.yuriisurzhykov.goaldestinator.goalscreation

interface StepParameter : java.io.Serializable {

    class GoalInput(
        private val goalName: String
    ) : StepParameter

    class GoalTasks(
        private val tasks: List<String>
    ) : StepParameter

    class DateInput(
        private val dateTime: Long
    ) : StepParameter
}
