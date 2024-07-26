package com.yuriisurzhykov.goaldestinator.goals.creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.yuriisurzhykov.goaldestinator.goalscreation.R
import com.yuriisurzhykov.goaldestinator.ui.DefaultPadding
import com.yuriisurzhykov.goaldestinator.ui.SaveableTextField
import com.yuriisurzhykov.goaldestinator.ui.TextHint

@Composable
fun CreateGoalInitialScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onNameChanged: (String) -> Unit,
    onNextClicked: () -> Unit
) {
    var goalNameString by rememberSaveable { mutableStateOf("") }
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = paddingValues.calculateStartPadding(layoutDirection),
                end = paddingValues.calculateEndPadding(layoutDirection)
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextHint(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DefaultPadding),
                stringRes = R.string.label_create_goal_first_step_description,
            )

            val keyboardController = LocalSoftwareKeyboardController.current

            SaveableTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = DefaultPadding, end = DefaultPadding),
                hintStringRes = R.string.hint_goal_create_name,
                onValueChanged = { string ->
                    goalNameString = string
                    onNameChanged.invoke(string)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        keyboardController?.hide()
                        onNextClicked.invoke()
                    }
                )
            )
        }
        Button(
            onClick = { onNextClicked.invoke() },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = stringResource(id = R.string.label_button_create_goal_next))
        }
    }
}