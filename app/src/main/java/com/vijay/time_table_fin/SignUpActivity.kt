package com.vijay.time_table_fin

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivitySignUpBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            (application as UserApplication).database.userDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setSpinner(resources.getStringArray(R.array.branch), binding.spinnerBranch) // Branch
        setSpinner(resources.getStringArray(R.array.sems), binding.spinnerSem)      // Semester
        setSpinner(resources.getStringArray(R.array.div), binding.spinnerDiv)       // Division
        signUp()
    }

    // sets the spinner
    private fun setSpinner(category: Array<String?>, spinner: Spinner) {
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, category)
    }

    private fun signUp() {
        val signUp_btn = binding.signUpBtn
        val intent = Intent(this, MainActivity::class.java)
        signUp_btn.setOnClickListener() {
            Log.d("SignUpActivity", "Entering logIn...")
            GlobalScope.launch {
                addNewUser()
                //startActivity(intent)
                Log.d("SignUpActivity", "User Added")
            }
        }
    }

    private fun validUsername(username: String?) : Boolean {
        val size = 3
        if (username == null || username.trim().length < size) {
            Log.d("SignUpActivity", "Username should be of size $size or greater")
            return false
        }
        Log.d("SignUpActivity", "Username is valid")
        return true
    }

    private fun validPassword(password: String?) : Boolean {
        val size = 3
        if (password == null || password.trim().length < size) {
            Log.d("SignUpActivity", "Password should be atleast of size $size")
            return false
        }
        Log.d("SignUpActivity", "Password is valid")
        return true
    }

    private fun addNewUser() {
        val username: String? = binding.userName.text.toString()
        val password: String? = binding.password.text.toString()
        if (!validUsername(username) || !validPassword(password)) {
            return
        }

        viewModel.addNewUser(
            username!!,
            password!!,
            binding.spinnerBranch.selectedItem.toString(),
            binding.spinnerSem.selectedItem.toString(),
            binding.spinnerDiv.selectedItem.toString()
        )

        Log.d("SignUpActivity", "User Added")
    }
}