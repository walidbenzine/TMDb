package com.example.iwatch.Activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iwatch.Entities.User
import com.example.iwatch.R
import com.example.iwatch.Dialogs.ChangePassword
import kotlinx.android.synthetic.main.activity_edit_profile.*


class EditProfile : AppCompatActivity(), ChangePassword.ChangePasswordDialogListener{

    private var user: User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        edit_profile_toolbar.title = "Edit Profile"
        setSupportActionBar(edit_profile_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get user information
        user = intent.getSerializableExtra("user") as User

        edit_first_name.setText(user?.firstName)
        edit_last_name.setText(user?.lastName)
        edit_email.setText(user?.email)
        edit_phone_number.setText(user?.mobile)
        edit_address.setText(user?.adresse)

        change_pwd.setOnClickListener {
            openDialog()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDialog() {
        val exampleDialog = ChangePassword()
        exampleDialog.show(supportFragmentManager, "example dialog")
    }

    override fun applyTexts(oldPassword: String?, newPassword: String?, confirmPassword: String?) {
        if (newPassword==confirmPassword){
            Toast.makeText(
                applicationContext,
                "mot de passe identiques",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
