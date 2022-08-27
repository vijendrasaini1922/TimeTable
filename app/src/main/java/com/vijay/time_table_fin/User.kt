package com.vijay.time_table_fin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var email: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "branch")
    var branch: String = "",
    @ColumnInfo(name = "sem")
    var sem: String = "",
    @ColumnInfo(name = "div")
    var div: String = ""
)