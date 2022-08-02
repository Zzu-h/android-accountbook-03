package com.woowa.accountbook.data.local.dao

import android.content.ContentValues
import androidx.core.database.getStringOrNull
import com.woowa.accountbook.data.local.*
import com.woowa.accountbook.data.local.entity.DBCategory
import com.woowa.accountbook.data.local.entity.DBHistory
import com.woowa.accountbook.data.local.entity.DBPayment
import javax.inject.Inject

class HistoryDao @Inject constructor(private val databaseHelper: DatabaseHelper) {

    fun getAllHistoryByMonth(filter: String, y: Int, m: Int): List<DBHistory> {

        val historyList = mutableListOf<DBHistory>()

        databaseHelper.readableDatabase.use { db ->
            val sql = """
               WITH TEMP_TABLE AS(
                   SELECT *
                   FROM $TABLE_HISTORY
                   INNER JOIN $TABLE_CATEGORY ON $TABLE_HISTORY.$HISTORY_CATEGORY_ID = $TABLE_CATEGORY.$CATEGORY_ID 
                   WHERE $TABLE_HISTORY.$HISTORY_YEAR = $y AND $TABLE_HISTORY.$HISTORY_MONTH = $m
               )
               SELECT * FROM TEMP_TABLE
               INNER JOIN $TABLE_PAYMENT ON TEMP_TABLE.$HISTORY_PAYMENT_ID = $TABLE_PAYMENT.$PAYMENT_ID
               ORDER BY TEMP_TABLE.$HISTORY_DAY ASC
            """.trimIndent()

            val cursor = db.rawQuery(sql, null)

            return cursor.use {
                while (it.moveToNext()) {
                    val id = it.getInt(8)
                    val price = it.getInt(0)
                    val type = it.getString(2)
                    val content = it.getStringOrNull(3)
                    val year = it.getInt(5)
                    val month = it.getInt(6)
                    val day = it.getInt(7)

                    val categoryId = it.getInt(12)
                    val categoryName = it.getString(9)
                    val categoryColor = it.getString(10)

                    val paymentName = it.getString(13)
                    val paymentId = it.getInt(14)

                    val history = DBHistory(
                        id = id,
                        price = price,
                        type = type,
                        content = content,
                        year = year,
                        month = month,
                        day = day,
                        category = DBCategory(
                            id = categoryId,
                            type = type,
                            title = categoryName,
                            color = categoryColor
                        ),
                        payment = DBPayment(
                            id = paymentId,
                            name = paymentName,
                            type = type
                        )
                    )
                    historyList.add(history)
                }
                historyList
            }
        }
    }

    fun addHistory(newHistory: DBHistory): Boolean {
        return try {
            databaseHelper.writableDatabase.use { database ->
                val contentValues = ContentValues().apply {
                    put(HISTORY_PRICE, newHistory.price)
                    put(HISTORY_CATEGORY_ID, newHistory.category.id)
                    put(HISTORY_CONTENT, newHistory.content)
                    put(HISTORY_TYPE, newHistory.type)
                    put(HISTORY_PAYMENT_ID, newHistory.payment.id)
                    put(HISTORY_YEAR, newHistory.year)
                    put(HISTORY_MONTH, newHistory.month)
                    put(HISTORY_DAY, newHistory.day)
                }
                database.insert(TABLE_HISTORY, null, contentValues)
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}