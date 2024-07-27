package com.yuriisurzhykov.goaldestinator.goals.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuriisurzhykov.goaldestinator.ui.DefaultContentPadding
import com.yuriisurzhykov.goaldestinator.ui.MainTheme

class GoalsListActivity : ComponentActivity() {

    private val viewModel: GoalsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Scaffold { paddingValues ->
                    val goals = viewModel.goals.collectAsStateWithLifecycle().value
                    GoalsListScreen(
                        goals = goals,
                        paddingValues = paddingValues,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(DefaultContentPadding)
                    ) { goal, checked ->
                        viewModel.onGoalCheckedChange(goal, checked)
                    }
                }
            }
        }
    }
}