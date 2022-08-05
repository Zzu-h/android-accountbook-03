package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.ui.theme.AccountbookTheme

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

@Preview
@Composable
fun TextFieldWithHintPreview() {
    AccountbookTheme {
        TextFieldWithHint(
            "1234",
            onValueChange = { str -> },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(),
        )
    }
}