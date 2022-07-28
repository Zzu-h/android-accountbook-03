package com.woowa.accountbook.data.dto

import com.woowa.accountbook.domain.model.Account
import java.util.*

data class AccountDto(
    val price: Int,
    val payment: String,
)

fun AccountDto.toAccount(): Account {
    return Account(null, price = price, payment = payment,date = Date())
}