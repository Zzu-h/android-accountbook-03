package com.woowa.accountbook.domain.model

data class Account(
    val id: Int = -1,
    val content: String?,
    val payment: String,
    val category: Category,
    val price: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val type: String
)