package com.vijay.time_table_fin

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val users: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}