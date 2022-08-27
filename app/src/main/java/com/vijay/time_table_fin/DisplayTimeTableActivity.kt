package com.vijay.time_table_fin

import android.content.DialogInterface
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE)
        // Hide the status bar and action bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_time_table)
        userViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(UserViewModel::class.java)

        getUser(userViewModel.auth.currentUser!!.email!!)
        edit_lock = true
    }

    private fun edit(view: View) {
        view as TextView
        if (edit_lock) {
            edit_lock = false
            view.setText("Set")
            return
        }

        view.setText("Edit")
        edit_lock = true
    }

    private fun setSubject(view: View) {

        if (edit_lock) {
            Toast.makeText(this, "Edit Menu Locked", Toast.LENGTH_SHORT).show()
            return
        }

        openDialog(view)
    }

    private fun setDatabase(tag: String, subject: String) {
        val day: Int = tag[0].minus('0')
        val classTime: Int = tag[1].minus('0')
        val start = getStart((8 * (day - 1)) + classTime - 1)
        val end = getEnd(start)
        user.subjects = user.subjects.substring(0, start) + subject + user.subjects.substring(end)
    }

    private fun getEnd(start: Int) : Int {
        var i = start
        while (i < user.subjects.length) {
            if (user.subjects[i] == ',') {
                break;
            }
            i++;
        }

        return i;
    }

    private fun getStart(lec: Int) : Int {
        var commas = 0
        var i = 0
        while (i < user.subjects.length) {
            if (commas == lec) {
                break;
            }
            if (user.subjects[i] == ',') {
                commas++;
            }
            i++;
        }

        return i
    }

    private fun getUser(email: String) {
        userViewModel.allUsers.observe(this, {
            it?.let {
                for (x in it) {
                    if (x.email == email) {
                        user = x
                    }
                }
            }
        })
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
                setDatabase(view.getTag().toString(), selected_subject)
            })
        alertDialog.create()
        alertDialog.show()
    }
}
