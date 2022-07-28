package com.woowa.accountbook.domain.model

import java.util.*

data class Income(
    val content: String?,
    val category: Category = Category(),
    val price: Int,
    val date: Date
)

fun Income.toAccount(): Account {
    return Account(content, price = price, payment = "", date = date, category = category)
}