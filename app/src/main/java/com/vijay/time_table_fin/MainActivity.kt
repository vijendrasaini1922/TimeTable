package com.vijay.time_table_fin

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      setContentView(R.layout.display_time_table)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val branches = resources.getStringArray(R.array.branch)
        val spinner_branch  = binding.spinnerBranch
        val adapter_branch = ArrayAdapter(this, android.R.layout.simple_spinner_item, branches)
        spinner_branch.adapter = adapter_branch

        spinner_branch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity,
                    getString(R.string.selected_item) + " " +
                            "" + branches[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val sems = resources.getStringArray(R.array.sems)
        val spinner_sem = binding.spinnerSem
        val adapter_sem = ArrayAdapter(this, android.R.layout.simple_spinner_item, sems)
        spinner_sem.adapter = adapter_sem

        spinner_sem.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity,
                    getString(R.string.selected_item) + " " +
                            "" + sems[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val login_button = binding.loginBtn
        val intent = Intent(this, DisplayTimeTableActivity::class.java)
        login_button.setOnClickListener(){
            startActivity(intent)


        }

    }
}