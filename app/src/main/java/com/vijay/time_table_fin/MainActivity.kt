package com.vijay.time_table_fin

import android.app.Application
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.vijay.time_table_fin.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: User? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(UserViewModel::class.java)
        logIn()
        signUp()
    }

    private fun signUp() {
        val signUp_button = binding.signUpBtn
        signUp_button.setOnClickListener() {
            Log.d("MainActivity", "Entering SignUp...")
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun logIn() {
        val login_button = binding.loginBtn
        val timeTableIntent = Intent(this, DisplayTimeTableActivity::class.java)
        login_button.setOnClickListener() { view ->
            Log.d("MainActivity", "Entering logIn...")
            val password: String? = binding.password.text.toString()
            getUser()
            if (user != null) {
                if (user!!.password == password) {
                    startActivity(timeTableIntent)
                } else {
                    Toast.makeText(this, "Password is wrong!!", Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity", "Password is wrong!!")
                }
            } else {
                Toast.makeText(this, "User doesn't exist!!", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "User doesn't exist!!")
            }
        }
    }

    private fun getUser() {
        Log.d("MainActivity", "Searching for user...")
        val username: String? = binding.userName.text.toString()
        if (!validUsername(username)) {
            return
        }

        userViewModel.allUsers.observe(this, {
            it?.let {
                for (x in it) {
                    if (x.username == username) {
                        user = x
                    }
                }
            }
        })
    }

    private fun validUsername(username: String?) : Boolean {
        if (username == null || username.trim().length == 0) {
            return false
        }
        Log.d("MainActivity", "Username is valid")
        return true
    }
}