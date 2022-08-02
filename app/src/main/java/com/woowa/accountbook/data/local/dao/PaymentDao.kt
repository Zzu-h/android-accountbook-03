package com.woowa.accountbook.data.local.dao

import android.content.ContentValues
import com.woowa.accountbook.data.local.*
import com.woowa.accountbook.data.local.entity.DBPayment
import javax.inject.Inject

class PaymentDao @Inject constructor(private val databaseHelper: DatabaseHelper) {

    fun getAllPayment(filter: String): List<DBPayment> {

        val categoryList = mutableListOf<DBPayment>()

        databaseHelper.readableDatabase.use { db ->
            val sql = """
               SELECT * FROM $TABLE_PAYMENT
            """.trimIndent()

            val cursor = db.rawQuery(sql, null)

            return cursor.use {
                while (it.moveToNext()) {
                    val id = it.getInt(2)
                    val name = it.getString(0)
                    val type = it.getString(1)
                    val category = DBPayment(
                        id = id,
                        type = type,
                        name = name,
                    )
                    categoryList.add(category)
                }
                categoryList
            }
        }
    }

    fun addPayment(newPayment: DBPayment): Boolean {
        return try {
            databaseHelper.writableDatabase.use { database ->
                val contentValues = ContentValues().apply {
                    put(CATEGORY_NAME, newPayment.name)
                    put(HISTORY_TYPE, newPayment.type)
                }
                database.insert(TABLE_PAYMENT, null, contentValues)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updatePayment(newPayment: DBPayment): Boolean {
        return try {
            databaseHelper.writableDatabase.use { database ->
                val contentValues = ContentValues().apply {
                    put(CATEGORY_NAME, newPayment.name)
                    put(HISTORY_TYPE, newPayment.type)
                }
                database.update(
                    TABLE_CATEGORY,
                    contentValues,
                    "$PAYMENT_ID=${newPayment.id}",
                    null
                )
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}