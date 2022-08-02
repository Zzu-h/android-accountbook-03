package com.woowa.accountbook.data.local.dao

import android.content.ContentValues
import com.woowa.accountbook.data.local.*
import com.woowa.accountbook.data.local.entity.DBCategory
import javax.inject.Inject

class CategoryDao @Inject constructor(private val databaseHelper: DatabaseHelper) {

    fun getAllCategory(filter: String): List<DBCategory> {

        val categoryList = mutableListOf<DBCategory>()

        databaseHelper.readableDatabase.use { db ->
            val sql = """
               SELECT * FROM $TABLE_CATEGORY
            """.trimIndent()

            val cursor = db.rawQuery(sql, null)

            return cursor.use {
                while (it.moveToNext()) {
                    val id = it.getInt(3)
                    val name = it.getString(0)
                    val type = it.getString(2)
                    val color = it.getString(1)
                    val category = DBCategory(
                        id = id,
                        type = type,
                        title = name,
                        color = color
                    )
                    categoryList.add(category)
                }
                categoryList
            }
        }
    }

    fun addCategory(newCategory: DBCategory): Boolean {
        return try {
            databaseHelper.writableDatabase.use { database ->
                val contentValues = ContentValues().apply {
                    put(CATEGORY_NAME, newCategory.title)
                    put(CATEGORY_COLOR, newCategory.color)
                    put(HISTORY_TYPE, newCategory.type)
                }
                database.insert(TABLE_CATEGORY, null, contentValues)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateCategory(newCategory: DBCategory): Boolean {
        return try {
            databaseHelper.writableDatabase.use { database ->
                val contentValues = ContentValues().apply {
                    put(CATEGORY_NAME, newCategory.title)
                    put(CATEGORY_COLOR, newCategory.color)
                    put(HISTORY_TYPE, newCategory.type)
                }
                database.update(
                    TABLE_CATEGORY,
                    contentValues,
                    "$CATEGORY_ID=${newCategory.id}",
                    null
                )
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}