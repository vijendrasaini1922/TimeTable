package com.vijay.time_table_fin

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val allUsers: LiveData<List<User>>
    val userRepository: UserRepository
    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        userRepository = UserRepository(userDao)
        allUsers = userRepository.users
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }

    private fun getNewUserEntry(username: String, password: String, branch: String, sem: String, div: String) : User {
        return User(
            username = username,
            password = password,
            branch = branch,
            sem = sem,
            div = div
        )
    }

    fun addNewUser(username: String, password: String, branch: String, sem: String, div: String) {
        val newUser = getNewUserEntry(username, password, branch, sem, div)
        addUser(newUser)
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}