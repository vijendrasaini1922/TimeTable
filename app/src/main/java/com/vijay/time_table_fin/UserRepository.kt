package com.vijay.time_table_fin

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class UserRepository(private val userDao: UserDao) {
    val users: LiveData<List<User>> = userDao.getAllUsers()
    val auth: FirebaseAuth = Firebase.auth
    val firestore = FirebaseFirestore.getInstance()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
        firestore.collection("users")
            .add(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}