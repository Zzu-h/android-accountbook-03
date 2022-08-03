package com.woowa.accountbook.domain.model

import java.io.Serializable

data class Payment(
    val id: Int,
    val name: String,
) : Serializable
