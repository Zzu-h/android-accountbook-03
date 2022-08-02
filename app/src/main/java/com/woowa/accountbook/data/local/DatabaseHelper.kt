package com.woowa.accountbook.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.addTypeExpenditure
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.addTypeIncome
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.createCategoryTable
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.createHistoryTable
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.createPaymentTable
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.createTypeTable
import com.woowa.accountbook.utils.TypeFilter
import javax.inject.Inject

class DatabaseHelper @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.apply {
            execSQL("DROP TABLE IF EXISTS $TABLE_PAYMENT")
            execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORY")
            execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")

            // create table
            execSQL(createPaymentTable)
            execSQL(createCategoryTable)
            execSQL(createHistoryTable)
            execSQL(createTypeTable)

            // input initial data
            execSQL(addTypeIncome)
            execSQL(addTypeExpenditure)
        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            database?.apply {
                execSQL("DROP TABLE IF EXISTS $TABLE_PAYMENT")
                execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORY")
                execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
            }
            onCreate(database)
        }
    }

    private object InitializeSql {
        val createPaymentTable = """
            CREATE TABLE $TABLE_PAYMENT (
                $PAYMENT_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
                $PAYMENT_NAME TEXT NOT NULL
            )
        """.trimIndent()

        val createCategoryTable = """
            CREATE TABLE $TABLE_CATEGORY (
                $CATEGORY_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                $CATEGORY_TYPE BOOLEAN NOT NULL, 
                $CATEGORY_NAME TEXT NOT NULL, 
                $CATEGORY_COLOR TEXT NOT NULL
            )
        """.trimIndent()

        val createHistoryTable = """
            CREATE TABLE $TABLE_HISTORY (
                $HISTORY_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                $HISTORY_PRICE INTEGER NOT NULL,
                $HISTORY_CATEGORY_ID INTEGER NOT NULL,
                $HISTORY_TYPE TEXT NOT NULL, 
                $HISTORY_CONTENT TEXT,
                $HISTORY_PAYMENT_ID INTEGER NOT NULL,
                $HISTORY_YEAR INTEGER NOT NULL,
                $HISTORY_MONTH INTEGER NOT NULL,
                $HISTORY_DAY INTEGER NOT NULL,
                FOREIGN KEY ($TABLE_CATEGORY) REFERENCES $TABLE_CATEGORY($CATEGORY_ID),
                FOREIGN KEY ($HISTORY_PAYMENT_ID) REFERENCES $TABLE_PAYMENT($PAYMENT_ID)
            )
        """.trimIndent()

        val createTypeTable = """
            CREATE TABLE $TABLE_TYPE ($TYPE_NAME TEXT NOT NULL PRIMARY KEY)
            
        """.trimIndent()

        val addTypeIncome =
            "INSERT INTO $TABLE_TYPE ($TYPE_NAME) VALUES (${TypeFilter.INCOME}".trim()

        val addTypeExpenditure =
            "INSERT INTO $TABLE_TYPE ($TYPE_NAME) VALUES (${TypeFilter.EXPENDITURE}".trim()
    }
}