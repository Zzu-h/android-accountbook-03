package com.woowa.accountbook.data.local

internal const val DATABASE_NAME = "AccountBook.db"
internal const val DATABASE_VERSION = 1

internal const val TABLE_HISTORY = "history"
internal const val HISTORY_ID = "_id"
internal const val HISTORY_PRICE = "price"
internal const val HISTORY_CATEGORY_ID = "category"
internal const val HISTORY_CONTENT = "content"
internal const val HISTORY_TYPE = "type"
internal const val HISTORY_PAYMENT_ID = "payment"
internal const val HISTORY_YEAR = "year"
internal const val HISTORY_MONTH = "month"
internal const val HISTORY_DAY = "day"

internal const val TABLE_CATEGORY = "category"
internal const val CATEGORY_ID = "_id"
internal const val CATEGORY_NAME = "name"
internal const val CATEGORY_COLOR = "color"
internal const val CATEGORY_TYPE = "type"

internal const val TABLE_PAYMENT = "payment"
internal const val PAYMENT_ID = "_id"
internal const val PAYMENT_NAME = "name"
internal const val PAYMENT_TYPE = "type"

internal const val TABLE_TYPE = "type"
internal const val TYPE_NAME = "name"