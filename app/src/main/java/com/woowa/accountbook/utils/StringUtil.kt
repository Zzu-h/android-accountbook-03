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
        return "${if (isMinus) '-' else ""}${getMoneyFormatString(price)} 원"
    }

    fun getMoneyFormatString(price: Int): String {
        val str = format.format(price.absoluteValue)

        return str.removeRange(0, 1)
    }
}