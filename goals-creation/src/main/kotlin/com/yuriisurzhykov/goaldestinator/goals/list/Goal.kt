package com.yuriisurzhykov.goaldestinator.goals.list

import androidx.compose.runtime.Immutable

@Immutable
data class Goal(
    val goalName: String,
    val description: String,
    val completed: Boolean
)