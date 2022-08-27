package com.vijay.time_table_fin

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserRepository(private val userDao: UserDao) {
    val users: LiveData<List<User>> = userDao.getAllUsers()
    val auth: FirebaseAuth = Firebase.auth

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}