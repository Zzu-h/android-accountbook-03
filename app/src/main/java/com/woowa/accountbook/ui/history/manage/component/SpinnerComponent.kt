package com.woowa.accountbook.ui.history.manage.component

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.theme.Purple700

@Composable
fun SpinnerComponent(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    properties: PopupProperties = PopupProperties(focusable = true),
    selectedString: (String) -> Unit
) {
    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = requestToOpen

    val roundCorner = 15.dp
    val thickness = 1.dp
    val widthSize = 0.7f

    if (expandedStates.currentState || expandedStates.targetState)
        Popup(
            onDismissRequest = { request(false) },
            popupPositionProvider = CustomPopupPositionProvider(LocalDensity.current),
            properties = properties
        ) {
            Box(
                modifier = Modifier
                    .background(color = Purple700, RoundedCornerShape(roundCorner))
                    .padding(thickness)
                    .fillMaxWidth(widthSize)
                //.clip(RoundedCornerShape(30.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(color = Color.White, RoundedCornerShape(roundCorner - (1).dp))
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    items(list) {
                        Text(
                            text = it, modifier = Modifier
                                .clickable(true) { selectedString(it) }
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            color = Purple700
                        )
                    }
                    item {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "추가하기", modifier = Modifier
                                    .clickable(true) { selectedString("ADD-TAG") }
                                    .padding(vertical = 8.dp)
                                    .align(Alignment.CenterStart),
                                color = Purple700
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add_24dp),
                                contentDescription = null,
                                tint = Purple700,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }
            }
        }
}

private class CustomPopupPositionProvider(private val density: Density) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val verticalMargin = with(density) { 48.dp.roundToPx() }
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetX = with(density) { 0.dp.roundToPx() }
        val contentOffsetY = with(density) { 0.dp.roundToPx() }

        val toLeft = anchorBounds.left + contentOffsetX
        val toRight = anchorBounds.right - contentOffsetX - popupContentSize.width
        val toDisplayRight = windowSize.width - popupContentSize.width
        val toDisplayLeft = 0

        val x = if (layoutDirection == LayoutDirection.Ltr) {
            sequenceOf(
                toRight,
                toLeft,
                // If the anchor gets outside of the window on the left, we want to position
                // toDisplayLeft for proximity to the anchor. Otherwise, toDisplayRight.
                if (anchorBounds.left >= 0) toDisplayRight else toDisplayLeft
            )
        } else {
            sequenceOf(
                toLeft,
                toRight,
                // If the anchor gets outside of the window on the right, we want to position
                // toDisplayRight for proximity to the anchor. Otherwise, toDisplayLeft.
                if (anchorBounds.right <= windowSize.width) toDisplayLeft else toDisplayRight
            )
        }.firstOrNull {
            it >= 0 && it + popupContentSize.width <= windowSize.width
        } ?: toLeft

        // Compute vertical position.
        val toBottom = maxOf(anchorBounds.bottom + contentOffsetY, verticalMargin)
        val toTop = anchorBounds.top - contentOffsetY - popupContentSize.height
        val toCenter = anchorBounds.top - popupContentSize.height / 2
        val toDisplayBottom = windowSize.height - popupContentSize.height - verticalMargin
        val y = sequenceOf(toBottom, toTop, toCenter, toDisplayBottom).firstOrNull {
            it >= verticalMargin &&
                    it + popupContentSize.height <= windowSize.height - verticalMargin
        } ?: toTop

        return IntOffset(x, y + 40)
    }
}