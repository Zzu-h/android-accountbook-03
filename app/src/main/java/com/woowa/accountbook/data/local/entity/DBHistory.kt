package com.woowa.accountbook.data.local.entity

data class DBHistory(
    val id: Int,
    val price: Int,
    val content: String?,
    val category: DBCategory,
    val year: Int,
    val month: Int,
    val day: Int,
    val payment: DBPayment?,
    val type: String,
)