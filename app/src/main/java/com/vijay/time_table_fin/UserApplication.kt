package com.vijay.time_table_fin

import android.app.Application

class UserApplication : Application() {
    val database: UserDataBase by lazy { UserDataBase.getDataBase(this) }
}