package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldWithHint(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    hint: @Composable () -> Unit = {},
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    cursorToLast: Boolean = false,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }

    val lastIdx = if (value.lastIndex < 0) 0 else value.length
    val textFieldValue =
        if (cursorToLast)
            TextFieldValue(text = value, selection = TextRange(lastIdx))
        else
            textFieldValueState.copy(text = value)

    Box(Modifier.fillMaxWidth()) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValueState = it
                onValueChange(it.text)
            },
            modifier = modifier,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            onTextLayout = onTextLayout,
            visualTransformation = visualTransformation
        )
        if (value.isEmpty()) hint()
    }
}