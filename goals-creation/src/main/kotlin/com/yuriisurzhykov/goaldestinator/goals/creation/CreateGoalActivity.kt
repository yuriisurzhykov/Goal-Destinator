package com.yuriisurzhykov.goaldestinator.goals.creation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.yuriisurzhykov.goaldestinator.goalscreation.R
import com.yuriisurzhykov.goaldestinator.ui.DefaultPadding
import com.yuriisurzhykov.goaldestinator.ui.MainTheme
import com.yuriisurzhykov.goaldestinator.ui.TitleText

class CreateGoalActivity : ComponentActivity() {

    private val viewModel: CreateGoalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = {
                            CreateGoalTopBar(modifier = Modifier)
                        }
                    ) { paddingValues ->
                        // TODO: Build navigation graph
                        CreateGoalInitialScreen(
                            paddingValues = paddingValues,
                            modifier = Modifier.fillMaxSize(),
                            onNameChanged = {
                                viewModel.updateGoalName(it)
                            }, onNextClicked = {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CreateGoalTopBar(modifier: Modifier = Modifier) {
    TitleText(
        modifier = modifier
            .fillMaxWidth()
            .padding(DefaultPadding),
        stringRes = R.string.label_create_goal_initial_title,
        textAlignment = TextAlign.Justify
    )
}