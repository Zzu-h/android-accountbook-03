package com.woowa.accountbook.ui.history.manage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.ui.theme.Purple700

@Composable
fun DropDownComponent(
    list: List<String>,
    selectItem: String? = null,
    onItemClick: (String) -> Unit = {}
) {
    val emptyFlag = selectItem == null || selectItem.isBlank()
    val noSelectComment = "선택하세요"
    var text = if (!emptyFlag) selectItem!! else noSelectComment
    val isOpen = remember { mutableStateOf(false) }
    var textColor = if (!emptyFlag) Purple700 else Purple200
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text = it
        onItemClick(it)
        isOpen.value = isOpen.value.not()
        textColor = Purple700
    }
    Box() {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = text,
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Icon(
                    painter = painterResource(id = if (isOpen.value) R.drawable.ic_up_24dp else R.drawable.ic_down_24dp),
                    contentDescription = null,
                    tint = Purple700,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            SpinnerComponent(
                requestToOpen = isOpen.value,
                list = list,
                request = openCloseOfDropDownList,
                selectedString = userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}