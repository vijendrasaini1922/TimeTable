package com.vijay.time_table_fin

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivitySignUpBinding

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
        val login_button = binding.signUpBtn
        val intent = Intent(this, MainActivity::class.java)
        login_button.setOnClickListener() {
            addNewUser()
            Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
            //startActivity(intent)
        }
    }

    private fun addNewUser() {
        viewModel.addNewUser(
            binding.userName.text.toString(),
            binding.password.text.toString(),
            binding.spinnerBranch.selectedItem.toString(),
            binding.spinnerSem.selectedItem.toString(),
            binding.spinnerDiv.selectedItem.toString()
        )
    }
}