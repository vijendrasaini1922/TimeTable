package com.vijay.time_table_fin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    private fun addUser(user: User) {
        viewModelScope.launch {
            userDao.addUser(user)
        }
    }

    private fun getNewUserEntry(username: String, password: String?, branch: String?, sem: String?, div: String?) : User {
        return User(
            username = username,
            password = password,
            branch = branch,
            sem = sem,
            div = div
        )
    }

    fun addNewUser(username: String, password: String?, branch: String?, sem: String?, div: String?) {
        val newUser = getNewUserEntry(username, password, branch, sem, div)
        addUser(newUser)
    }

    fun getUser(username: String) : User {
        return userDao.getUser(username)
    }
}

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}