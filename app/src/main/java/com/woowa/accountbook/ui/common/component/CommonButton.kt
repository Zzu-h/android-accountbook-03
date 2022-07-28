package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.DisableYellow
import com.woowa.accountbook.ui.theme.Yellow

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    isActive: Boolean = true,
) {
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = Yellow,
        disabledBackgroundColor = DisableYellow,
        contentColor = White,
        disabledContentColor = White,
    )
    val modifiers = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .composed { modifier }

    Button(
        onClick = onClick,
        modifier = modifiers,
        enabled = isActive,
        colors = buttonColor,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(14)
    ) { Text(text = text) }
}