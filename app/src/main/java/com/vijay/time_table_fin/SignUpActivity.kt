package com.vijay.time_table_fin

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.vijay.time_table_fin.databinding.ActivitySignUpBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var auth: FirebaseAuth

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

        auth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            val email = binding.userName.text.toString().trim()
            val password = binding.password.text.toString().trim()
            signUp(email, password)
        }
    }

    // sets the spinner
    private fun setSpinner(category: Array<String?>, spinner: Spinner) {
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, category)
    }

    private fun signUp(email: String, password: String) {
//        val signUp_btn = binding.signUpBtn
//        val intent = Intent(this, MainActivity::class.java)
//        signUp_btn.setOnClickListener() {
//            Log.d("SignUpActivity", "Entering logIn...")
//            GlobalScope.launch {
//                addNewUser()
//                startActivity(intent)
//                Log.d("SignUpActivity", "User Added")
//            }
//        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Authentication Success.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

//    private fun validUsername(username: String?) : Boolean {
//        val size = 3
//        if (username == null || username.trim().length < size) {
//            Log.d("SignUpActivity", "Username should be of size $size or greater")
//            return false
//        }
//        Log.d("SignUpActivity", "Username is valid")
//        return true
//    }
//
//    private fun validPassword(password: String?) : Boolean {
//        val size = 3
//        if (password == null || password.trim().length < size) {
//            Log.d("SignUpActivity", "Password should be atleast of size $size")
//            return false
//        }
//        Log.d("SignUpActivity", "Password is valid")
//        return true
//    }
//
//    private fun addNewUser() {
//        val username: String? = binding.userName.text.toString()
//        val password: String? = binding.password.text.toString()
//        if (!validUsername(username) || !validPassword(password)) {
//            return
//        }
//
//        userViewModel.addNewUser(
//            username!!,
//            password!!,
//            binding.spinnerBranch.selectedItem.toString(),
//            binding.spinnerSem.selectedItem.toString(),
//            binding.spinnerDiv.selectedItem.toString()
//        )
//
//        Log.d("SignUpActivity", "User Added")
//    }
}