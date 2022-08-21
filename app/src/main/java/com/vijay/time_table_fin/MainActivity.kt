package com.vijay.time_table_fin

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            (application as UserApplication).database.userDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        logIn()
    }

    fun logIn() {
        val login_button = binding.loginBtn
        val timeTableIntent = Intent(this, DisplayTimeTableActivity::class.java)
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        login_button.setOnClickListener() { view->
            val username: String? = binding.userName.text.toString()
            val password: String? = binding.password.text.toString()
            if (validUsername(username)) {
                user = viewModel.getUser(username!!)!!
                if (user.password == password) {
                    startActivity(timeTableIntent)
                } else {
                    Toast.makeText(this, "Password is wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "$username doesn't exist", Toast.LENGTH_SHORT).show()
                startActivity(signUpIntent)
            }
        }
    }

    fun validUsername(username: String?) : Boolean {
        if (username == null || username.trim() == null || username.trim().length == 0) {
            return false
        }
        return false
    }
}