package com.woowa.accountbook.domain.model

import java.io.Serializable

data class History(
    val id: Int = -1,
    val content: String?,
    val payment: String,
    val category: Category,
    val price: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val type: String
) : Serializable