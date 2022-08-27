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
            GlobalScope.launch {
                getUser()
                if (user != null) {
                    if (user!!.password == password) {
                        startActivity(timeTableIntent)
                    } else {
                        Log.d("MainActivity", "Password is wrong!!")
                    }
                } else {
                    Log.d("MainActivity", "User doesn't exist!!")
                }
            }
        }
    }

    suspend private fun getUser() {
        Log.d("MainActivity", "Searching for user...")
        val username: String? = binding.userName.text.toString()
        if (validUsername(username)) {
//            user = userViewModel.getUser(username!!)

        }
    }

    private fun validUsername(username: String?) : Boolean {
        if (username == null || username.trim().length == 0) {
            return false
        }
        Log.d("MainActivity", "Username is valid")
        return true
    }
}