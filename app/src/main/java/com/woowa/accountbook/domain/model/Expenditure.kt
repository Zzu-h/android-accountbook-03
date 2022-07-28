package com.woowa.accountbook.domain.model

import java.util.*

data class Expenditure(
    val content: String?,
    val payment: String,
    val category: Category = Category(),
    val price: Int,
    val date: Date
)

fun Expenditure.toAccount(): Account {
    return Account(content, price = price, payment = payment, date = date, category = category)
}