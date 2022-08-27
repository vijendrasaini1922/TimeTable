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
}

//class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        Log.d("Factory", "Might be a problem!!!!")
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            Log.d("Factory", "No problem with the factory chief!")
//            @Suppress("UNCHECKED_CAST")
//            return UserViewModel(userDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}