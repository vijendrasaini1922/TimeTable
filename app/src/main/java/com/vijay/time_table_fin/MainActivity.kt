package com.vijay.time_table_fin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSpinner(resources.getStringArray(R.array.branch), binding.spinnerBranch) // Branch
        setSpinner(resources.getStringArray(R.array.sems), binding.spinnerSem)      // Semester
        setSpinner(resources.getStringArray(R.array.div), binding.spinnerDiv)       // Division
        logIn()
    }

    // sets the spinner
    fun setSpinner(category: Array<String?>, spinner: Spinner) {
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, category)
    }

    fun logIn() {
        val login_button = binding.loginBtn
        val intent = Intent(this, DisplayTimeTableActivity::class.java)
        login_button.setOnClickListener() {
            startActivity(intent)
        }
    }
}