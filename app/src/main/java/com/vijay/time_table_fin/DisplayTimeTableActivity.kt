package com.vijay.time_table_fin

import android.content.DialogInterface
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.vijay.time_table_fin.databinding.ActivityDisplayTimeTableBinding
import org.w3c.dom.Text

class DisplayTimeTableActivity: AppCompatActivity() {
    private lateinit var binding:ActivityDisplayTimeTableBinding
    private var edit_lock: Boolean = true
    private lateinit var listView: ListView
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var alertDialog: AlertDialog.Builder
    private lateinit var dialog:AlertDialog
    lateinit var selected_subject:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE)
        // Hide the status bar and action bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_time_table)

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
        // TODO select subject in a spinner
        // TODO create new subject by taking input from user

        openDialog(view)
    }

    fun openDialog(view:View) {
        val subjects_array = resources.getStringArray(R.array.subjects)
        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.select_subject)
            .setItems(subjects_array,
            DialogInterface.OnClickListener{View, which ->
                selected_subject = subjects_array[which]
                view as TextView
                view.setText(selected_subject)
            })
        alertDialog.create()
        alertDialog.show()
    }
}
