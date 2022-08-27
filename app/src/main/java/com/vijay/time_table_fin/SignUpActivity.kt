package com.vijay.time_table_fin

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vijay.time_table_fin.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setSpinner(resources.getStringArray(R.array.branch), binding.spinnerBranch) // Branch
        setSpinner(resources.getStringArray(R.array.sems), binding.spinnerSem)      // Semester
        setSpinner(resources.getStringArray(R.array.div), binding.spinnerDiv)       // Division
        userViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(UserViewModel::class.java)

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
            var added = addNewUser()
            if (added) {
                startActivity(intent)
                Log.d("SignUpActivity", "User Added")
            }
        }
    }

    private fun validEmail(email: String?) : Boolean {
        val size = 3
        if ((email == null || email.trim().length < size) && (email!!.contains("@gmail.com") || email.contains("@coed.svnit.ac.in"))) {
            Toast.makeText(this, "Include @gmail.com or @coed.svnit.ac.in", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validPassword(password: String?) : Boolean {
        val size = 3
        if (password == null || password.trim().length < size) {
            Toast.makeText(this, "Password cannot be null", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun createUser(email: String, password: String) {
        userViewModel.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                }
            }
    }

    private fun addNewUser() : Boolean {
        val email: String? = binding.email.text.toString()
        val password: String? = binding.password.text.toString()
        if (!validEmail(email) || !validPassword(password)) {
            return false
        }

        createUser(email!!, password!!)
        userViewModel.addNewUser(
            email,
            password,
            binding.spinnerBranch.selectedItem.toString(),
            binding.spinnerSem.selectedItem.toString(),
            binding.spinnerDiv.selectedItem.toString()
        )

        return true
    }
}