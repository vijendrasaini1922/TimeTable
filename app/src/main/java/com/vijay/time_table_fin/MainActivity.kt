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
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun logIn() {
        val login_button = binding.loginBtn
        val timeTableIntent = Intent(this, DisplayTimeTableActivity::class.java)
        login_button.setOnClickListener() { view ->
            val email: String? = binding.email.text.toString()
            val password: String? = binding.password.text.toString()
            if(validEmail(email) && validPassword(password)) {
                userViewModel.auth.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(timeTableIntent)
                        }
                    }
            }
        }
    }

    private fun getUser() {
        val email: String? = binding.email.text.toString()
        if (!validEmail(email)) {
            return
        }

        userViewModel.allUsers.observe(this, {
            it?.let {
                for (x in it) {
                    if (x.email == email) {
                        user = x
                    }
                }
            }
        })
    }

    private fun validEmail(email: String?) : Boolean {
        val size = 3
        if (email == null || email.trim().length < size || !email.contains("@gmail.com") || !email.contains("@coed.svnit.ac.in")) {
            return false
        }
        return true
    }

    private fun validPassword(password: String?) : Boolean {
        val size = 3
        if (password == null || password.trim().length < size) {
            return false
        }
        return true
    }
}