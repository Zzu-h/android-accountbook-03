package com.woowa.accountbook.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AccountApplication : Application() {
    companion object{
        const val TAG = "TEST-APP"
    }
}