package com.woowa.accountbook.utils

import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

object StringUtil {

    private val format: NumberFormat = NumberFormat.getCurrencyInstance()
        .apply {
            maximumFractionDigits = 0
            currency = Currency.getInstance("KRW")
        }

    fun getPriceToString(price: Int, isMinus: Boolean): String {
        val str = format.format(price.absoluteValue)

        return "${if (isMinus) '-' else ""}$str 원"
    }

    fun getDateToString() {}
}

