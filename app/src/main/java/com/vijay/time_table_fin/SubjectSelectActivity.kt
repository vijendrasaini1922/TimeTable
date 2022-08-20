package com.vijay.time_table_fin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivitySubjectSelectBinding

class SubjectSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_subject_select)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_subject_select)
    }
}