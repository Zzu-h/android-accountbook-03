package com.woowa.accountbook.domain.model

object AccountType {
    const val INCOME = "INCOME"
    const val EXPENDITURE = "EXPENDITURE"
}

data class Account(
    val content: String?,
    val payment: String,
    val category: Category = Category(),
    val price: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val type: String
)