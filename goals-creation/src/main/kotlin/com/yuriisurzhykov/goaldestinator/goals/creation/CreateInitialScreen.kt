package com.yuriisurzhykov.goaldestinator.goals.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.yuriisurzhykov.goaldestinator.goals.creation.common.DefaultPadding
import com.yuriisurzhykov.goaldestinator.goals.creation.common.SaveableTextField
import com.yuriisurzhykov.goaldestinator.goals.creation.common.TextHint
import com.yuriisurzhykov.goaldestinator.goals.creation.common.TitleText
import com.yuriisurzhykov.goaldestinator.goalscreation.R

@Composable
fun CreateGoalInitialScreen(
    modifier: Modifier = Modifier,
    onNameChanged: (String) -> Unit,
    onNextClicked: KeyboardActionScope.() -> Unit
) {
    var goalNameString by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultPadding),
            stringRes = R.string.label_create_goal_initial_title,
            textAlignment = TextAlign.Justify
        )
        TextHint(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DefaultPadding),
            stringRes = R.string.label_create_goal_first_step_description,
        )
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
                onNext = onNextClicked
            )
        )
    }
}