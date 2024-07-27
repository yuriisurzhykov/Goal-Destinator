package com.yuriisurzhykov.goaldestinator.goals.creation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yuriisurzhykov.goaldestinator.goalscreation.R
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoalDateScreen(paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    val date = LocalDateTime.now()
    val dateFormatter = remember { DatePickerFormatter() }
    val state = rememberDatePickerState(
        yearRange = (date.year..date.year + 10),
        initialSelectedDateMillis = date.toMillis(),
        initialDisplayMode = DisplayMode.Input,
        initialDisplayedMonthMillis = null
    )
    val layoutDirection = LocalLayoutDirection.current
    DatePicker(
        modifier = modifier.padding(
            top = paddingValues.calculateTopPadding(),
            start = paddingValues.calculateStartPadding(layoutDirection),
            end = paddingValues.calculateEndPadding(layoutDirection),
            bottom = paddingValues.calculateBottomPadding()
        ),
        dateFormatter = dateFormatter,
        state = state,
        title = {
            Text(
                text = stringResource(id = R.string.label_create_goal_select_date),
                modifier = Modifier.padding(PaddingValues(start = 24.dp, end = 12.dp, top = 16.dp))
            )
        }
    )
}