package com.vijay.time_table_fin

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class DisplayTimeTableActivity: AppCompatActivity() {
    var subjects = arrayOf(
        "OS", "PPA", "CN",
        "ML", "PEEBM", "Branch Specific"
    )

    private var edit_lock: Boolean = true
    private lateinit var listView: ListView
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var alertDialog: AlertDialog.Builder
    private lateinit var dialog:AlertDialog


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
        openDialog(view)

        //Toast.makeText(this, "Select Subject", Toast.LENGTH_SHORT).show()

        // TODO select subject in a spinner
        // TODO create new subject by taking input from user
    }

    fun openDialog(view: View) {
        alertDialog = AlertDialog.Builder(this)
        val rowList: View = layoutInflater.inflate(R.layout.row, null)
        listView = rowList.findViewById(R.id.list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        alertDialog.setView(rowList)
        dialog = alertDialog.create()
        dialog.show()
    }

    fun closeDialog(view: View) {
        dialog.dismiss()
        Toast.makeText(baseContext, "Dialog Closed", Toast.LENGTH_SHORT).show()
    }
}
