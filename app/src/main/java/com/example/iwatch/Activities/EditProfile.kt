package com.example.iwatch.Activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iwatch.Dialogs.ChangePassword
import com.example.iwatch.Entities.User
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_edit_profile.*


class EditProfile : AppCompatActivity(), ChangePassword.ChangePasswordDialogListener{

    private var user: User?=null
    private var post = PostClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        edit_profile_toolbar.title = "Edit Profile"
        setSupportActionBar(edit_profile_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get user information
        user = intent.getSerializableExtra("user") as User
        edit_login.setText(user?.login)
        edit_first_name.setText(user?.firstName)
        edit_last_name.setText(user?.lastName)
        edit_email.setText(user?.email)
        edit_phone_number.setText(user?.mobile)
        edit_address.setText(user?.adresse)

        change_pwd.setOnClickListener {
            openDialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.profile_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //go back to profil fragment
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        //save modifications
        else if(item.itemId == R.id.menu_save_profile){
            user?.login = edit_login.text.toString()
            user?.firstName = edit_first_name.text.toString()
            user?.lastName = edit_last_name.text.toString()
            user?.email = edit_email.text.toString()
            user?.mobile = edit_phone_number.text.toString()
            user?.adresse = edit_address.text.toString()
           // Post("http://scirusiwatch.herokuapp.com/changeinfo/" + user?.id + "/" + +"/" + +"/" + +"/" + +"/" + )
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDialog() {
        val exampleDialog = ChangePassword()
        exampleDialog.show(supportFragmentManager, "example dialog")
    }

    override fun applyTexts(oldPassword: String?, newPassword: String?, confirmPassword: String?) {
        if (newPassword==confirmPassword){

            post.PostArray("http://scirusiwatch.herokuapp.com/changepass/" + user?.id + "/" + confirmPassword)
            Toast.makeText(
                applicationContext,
                "mot de passe a été bien changé",
                Toast.LENGTH_SHORT
            ).show()
        }
        else{
            Toast.makeText(
                applicationContext,
                "confirm password is not correct!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
