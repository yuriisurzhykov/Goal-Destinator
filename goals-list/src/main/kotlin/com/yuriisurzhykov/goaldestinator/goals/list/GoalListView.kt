package com.yuriisurzhykov.goaldestinator.goals.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.yuriisurzhykov.goaldestinator.ui.InputCornerRadius

@Composable
fun GoalListView(goal: Goal, modifier: Modifier = Modifier, onGoalChecked: (Boolean) -> Unit) {
    var checked by remember {
        mutableStateOf(goal.completed)
    }
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable { onGoalChecked.invoke(checked) },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier.clip(RoundedCornerShape(InputCornerRadius)),
            checked = goal.completed, onCheckedChange = {
                checked = it
                onGoalChecked.invoke(it)
            }
        )
        Text(
            text = goal.goalName, modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true),
            style = MaterialTheme.typography.labelLarge.let {
                if (goal.completed) it.copy(textDecoration = TextDecoration.LineThrough) else it
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGoalListView(modifier: Modifier = Modifier) {
    GoalListView(
        modifier = modifier,
        goal = Goal(
            "Lorem ipsum dolor sit amet",
            "The description of the future task to do, which is not finished yet",
            false
        )
    ) {

    }
}