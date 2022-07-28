package com.woowa.accountbook.domain.model

import java.util.*

data class Account(
    val content: String?,
    val payment: String,
    val category: Category = Category(),
    val price: Int,
    val date: Date
)