package com.woowa.accountbook.domain.model

object AccountType{
    const val INCOME = true
    const val EXPENDITURE = false
}

data class Account(
    val content: String?,
    val payment: String,
    val category: Category = Category(),
    val price: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val type: Boolean
)