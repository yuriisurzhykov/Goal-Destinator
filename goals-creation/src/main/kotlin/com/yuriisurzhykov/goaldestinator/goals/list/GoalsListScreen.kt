package com.yuriisurzhykov.goaldestinator.goals.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuriisurzhykov.goaldestinator.goalscreation.R
import com.yuriisurzhykov.goaldestinator.ui.DefaultContainerCornerRadius
import com.yuriisurzhykov.goaldestinator.ui.DefaultContentPadding
import com.yuriisurzhykov.goaldestinator.ui.DefaultPadding

@Composable
fun GoalsListScreen(
    goals: List<Goal>,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onGoalCheckedChanged: (Goal, Boolean) -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current
    val groupedGoals = goals.groupBy { it.completed }
    LazyColumn(
        modifier = modifier
            .padding(
                start = paddingValues.calculateStartPadding(layoutDirection),
                end = paddingValues.calculateEndPadding(layoutDirection),
                bottom = paddingValues.calculateBottomPadding(),
                top = paddingValues.calculateTopPadding()
            )
    ) {
        item {
            ProgressIndicator(
                percentage = if (goals.isEmpty()) 100f
                else (groupedGoals[true].orEmpty().size * 100 / goals.size).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DefaultContentPadding)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Column(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(DefaultContainerCornerRadius)
                )
                .fillMaxSize()
                .padding(DefaultContentPadding)
        ) {
            LazyColumn(modifier = Modifier.verticalScroll(rememberScrollState())) {
                item {
                    Text(
                        text = stringResource(id = R.string.label_title_tasks_in_progress),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = DefaultPadding, end = DefaultPadding),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                items(groupedGoals[false].orEmpty()) { goal ->
                    GoalListView(goal = goal) { checked ->
                        onGoalCheckedChanged.invoke(goal, checked)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {
                    Text(
                        text = stringResource(id = R.string.label_title_tasks_completed),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = DefaultPadding, end = DefaultPadding),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                items(groupedGoals[true].orEmpty()) { goal ->
                    GoalListView(goal = goal) { checked ->
                        onGoalCheckedChanged.invoke(goal, checked)
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressIndicator(
    percentage: Float,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.label_progress_indicator),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
            )
            Text(
                text = stringResource(id = R.string.label_percentage_format)
                    .format(percentage.toInt())
            )
        }
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = percentage / 100f,
            color = colorResource(id = R.color.color_progress_indicator_bar),
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Preview
@Composable
fun PreviewProgressIndicator(modifier: Modifier = Modifier) {
    ProgressIndicator(percentage = 50f)
}