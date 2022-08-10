package com.vijay.time_table_fin

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class DisplayTimeTableActivity: AppCompatActivity() {
    var edit_lock: Boolean = true
    var subjects = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE)

        // Hide the status bar and action bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        setContentView(R.layout.activity_display_time_table)
        edit_lock = true
    }

    fun edit(view: View) {
        view as TextView
        if (edit_lock) {
            edit_lock = false
            view.setText("Set")
            return
        }

        view.setText("Edit")
        edit_lock = true
    }

    fun setSubject(view: View) {
        if (edit_lock) {
            Toast.makeText(this, "Edit Menu Locked", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "Select Subject", Toast.LENGTH_SHORT).show()

        // TODO select subject in a spinner
        // TODO create new subject by taking input from user
    }
}
