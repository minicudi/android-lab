package com.example.trainings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrainingOutlinedTextBox(
    label: String,
    value: String,
    fontSize: Int = 16,
    onValueChange: (String) -> Unit,
    keyboard: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier,
    errorValue: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    hideKeyboard: Boolean = false,
    placeholderText: String = "",
    isEnabled: Boolean = true,
    onFocusClear: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }

    OutlinedTextField(
        label = { Text(text = label, fontSize = fontSize.sp) },
        value = value,
        placeholder = { Text(text = placeholderText) },
        onValueChange = onValueChange,
        modifier = Modifier.run {
            if (errorValue != "") {
                then(modifier)
                    .padding(bottom = 13.dp)
            } else {
                then(modifier)
            }
        },
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboard,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.clearFocus() }
        ),
        singleLine = singleLine,
        minLines = minLines,
        maxLines = if (singleLine) 1 else maxLines,
        supportingText = {
            if (errorValue.isNotBlank() && errorValue != "") {
                Text(text = errorValue, style = MaterialTheme.typography.bodyMedium)
            }
        },
        isError = errorValue.isNotBlank() && errorValue != "",
        textStyle = MaterialTheme.typography.bodyMedium,
        enabled = isEnabled
    )
}