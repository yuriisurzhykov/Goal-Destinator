package com.yuriisurzhykov.goaldestinator.goals.creation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class CreateGoalActivity : ComponentActivity() {

    private val viewModel: CreateGoalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: Build navigation graph
            CreateGoalInitialScreen(
                onNameChanged = {
                    viewModel.updateGoalName(it)
                }, onNextClicked = {

                }
            )
        }
    }
}