package com.vijay.time_table_fin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table2")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "email")
    var email: String = "",
    @ColumnInfo(name = "branch")
    var branch: String = "",
    @ColumnInfo(name = "sem")
    var sem: String = "",
    @ColumnInfo(name = "div")
    var div: String = ""
)