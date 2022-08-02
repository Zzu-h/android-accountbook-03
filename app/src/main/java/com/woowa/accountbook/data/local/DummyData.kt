package com.woowa.accountbook.data.local

import com.woowa.accountbook.ui.theme.Red
import com.woowa.accountbook.ui.theme.Yellow
import com.woowa.accountbook.utils.TypeFilter

internal object DummyData {
    val categoryOfIncome =
        "INSERT INTO $TABLE_CATEGORY ($CATEGORY_NAME, $CATEGORY_COLOR, $CATEGORY_TYPE) VALUES (\"미분류\", \"${Yellow.value}\" ,\"${TypeFilter.INCOME}\")".trim()
    val categoryOfExpenditure =
        "INSERT INTO $TABLE_CATEGORY ($CATEGORY_NAME, $CATEGORY_COLOR, $CATEGORY_TYPE) VALUES (\"미분류\", \"${Red.value}\" ,\"${TypeFilter.EXPENDITURE}\")".trim()

    val payment =
        "INSERT INTO $TABLE_PAYMENT ($PAYMENT_NAME, $PAYMENT_TYPE) VALUES (\"미분류\", \"${TypeFilter.EXPENDITURE}\")".trim()

    val historyOfIncome = """
            INSERT INTO $TABLE_HISTORY
             ($HISTORY_PRICE, $HISTORY_CONTENT, $HISTORY_CATEGORY_ID, $HISTORY_YEAR, $HISTORY_MONTH, $HISTORY_DAY, $HISTORY_PAYMENT_ID, $HISTORY_TYPE)
            VALUES 
             ( 9200, "테스트입니다.", 1, 2022, 7, 23, 1, "${TypeFilter.INCOME}" )
        """.trimIndent()

    val historyOfExpenditure = """
            INSERT INTO $TABLE_HISTORY
             ($HISTORY_PRICE, $HISTORY_CONTENT, $HISTORY_CATEGORY_ID, $HISTORY_YEAR, $HISTORY_MONTH, $HISTORY_DAY, $HISTORY_PAYMENT_ID, $HISTORY_TYPE)
            VALUES 
             ( 10200, "테스트2입니다.", 2, 2022, 7, 25, 2, "${TypeFilter.EXPENDITURE}" )
        """.trimIndent()

    val historyOfIncome2 = """
            INSERT INTO $TABLE_HISTORY
             ($HISTORY_PRICE, $HISTORY_CONTENT, $HISTORY_CATEGORY_ID, $HISTORY_YEAR, $HISTORY_MONTH, $HISTORY_DAY, $HISTORY_PAYMENT_ID, $HISTORY_TYPE)
            VALUES 
             ( 9200, "테스트입니다.", 1, 2022, 8, 23, 1, "${TypeFilter.INCOME}" )
        """.trimIndent()

    val historyOfExpenditure2 = """
            INSERT INTO $TABLE_HISTORY
             ($HISTORY_PRICE, $HISTORY_CONTENT, $HISTORY_CATEGORY_ID, $HISTORY_YEAR, $HISTORY_MONTH, $HISTORY_DAY, $HISTORY_PAYMENT_ID, $HISTORY_TYPE)
            VALUES 
             ( 10200, "테스트2입니다.", 2, 2022, 8, 25, 2, "${TypeFilter.EXPENDITURE}" )
        """.trimIndent()
}