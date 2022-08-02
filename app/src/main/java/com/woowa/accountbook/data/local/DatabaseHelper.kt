package com.woowa.accountbook.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.woowa.accountbook.data.local.DatabaseHelper.InitializeSql.addPaymentIncome
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
            execSQL("DROP TABLE IF EXISTS $TABLE_TYPE")

            // create table
            execSQL(createTypeTable)
            execSQL(createPaymentTable)
            execSQL(createCategoryTable)
            execSQL(createHistoryTable)

            // input initial data
            execSQL(addTypeIncome)
            execSQL(addTypeExpenditure)
            execSQL(addPaymentIncome)

            // input dummy data
            execSQL(DummyData.categoryOfIncome)
            execSQL(DummyData.categoryOfExpenditure)
            execSQL(DummyData.payment)
            execSQL(DummyData.historyOfIncome)
            execSQL(DummyData.historyOfExpenditure)
            execSQL(DummyData.historyOfIncome2)
            execSQL(DummyData.historyOfExpenditure2)
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
            CREATE TABLE IF NOT EXISTS $TABLE_PAYMENT (
                $PAYMENT_NAME TEXT NOT NULL,
                $PAYMENT_TYPE TEXT NOT NULL,
                ${BaseColumns._ID} INTEGER PRIMARY KEY
            )
        """.trimIndent()

        val createCategoryTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_CATEGORY (
                $CATEGORY_NAME TEXT NOT NULL, 
                $CATEGORY_COLOR TEXT NOT NULL,
                $CATEGORY_TYPE TEXT NOT NULL, 
                ${BaseColumns._ID} INTEGER PRIMARY KEY,
                FOREIGN KEY ($CATEGORY_TYPE) REFERENCES $TABLE_TYPE($TYPE_NAME)
            )
        """.trimIndent()

        val createHistoryTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_HISTORY (
                $HISTORY_PRICE INTEGER NOT NULL,
                $HISTORY_CATEGORY_ID INTEGER NOT NULL,
                $HISTORY_TYPE TEXT NOT NULL, 
                $HISTORY_CONTENT TEXT,
                $HISTORY_PAYMENT_ID INTEGER,
                $HISTORY_YEAR INTEGER NOT NULL,
                $HISTORY_MONTH INTEGER NOT NULL,
                $HISTORY_DAY INTEGER NOT NULL,
                ${BaseColumns._ID} INTEGER PRIMARY KEY,
                FOREIGN KEY ($TABLE_CATEGORY) REFERENCES $TABLE_CATEGORY($CATEGORY_ID),
                FOREIGN KEY ($HISTORY_PAYMENT_ID) REFERENCES $TABLE_PAYMENT($PAYMENT_ID),
                FOREIGN KEY ($HISTORY_TYPE) REFERENCES $TABLE_TYPE($TYPE_NAME)
            )
        """.trimIndent()

        val createTypeTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_TYPE ($TYPE_NAME TEXT NOT NULL PRIMARY KEY)
            
        """.trimIndent()

        val addTypeIncome =
            "INSERT INTO $TABLE_TYPE VALUES (\"${TypeFilter.INCOME}\")".trim()

        val addTypeExpenditure =
            "INSERT INTO $TABLE_TYPE VALUES (\"${TypeFilter.EXPENDITURE}\")".trim()

        val addPaymentIncome =
            "INSERT INTO $TABLE_PAYMENT ($PAYMENT_NAME, $PAYMENT_TYPE) VALUES (\"${TypeFilter.INCOME}\", \"${TypeFilter.INCOME}\")".trim()
    }
}