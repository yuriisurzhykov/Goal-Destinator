package com.yuriisurzhykov.goaldestinator.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

val DynamicTextFieldColors
    @Composable
    get() = OutlinedTextFieldDefaults.colors(
        /*
        focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        focusedBorderColor = MaterialTheme.colorScheme.primary
        */
    )

@Composable
fun TitleText(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start
) {
    Text(
        text = stringResource(stringRes),
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium.copy(textAlign = textAlignment)
    )
}

@Composable
fun TextHint(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start
) {
    Text(
        text = stringResource(stringRes),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium.copy(
            textAlign = textAlignment,
            fontWeight = FontWeight.Normal
        )
    )
}

@Composable
fun SaveableTextField(
    @StringRes hintStringRes: Int,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var fieldInput by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier,
        value = fieldInput,
        onValueChange = { string ->
            fieldInput = string
            onValueChanged.invoke(string)
        },
        placeholder = {
            Text(
                text = stringResource(id = hintStringRes),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        },
        shape = RoundedCornerShape(InputCornerRadius),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        colors = DynamicTextFieldColors
    )
}