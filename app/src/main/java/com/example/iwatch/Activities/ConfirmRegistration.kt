package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_confirm_registration.*
import android.widget.Toast
import com.example.iwatch.Entities.User
import java.net.URL

class ConfirmRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)

        field_one.addTextChangedListener(
            object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_one.text.length>0){
                        field_two.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })

        field_two.addTextChangedListener(
            object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_two.text.length>0){
                        field_three.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

        field_three.addTextChangedListener(
            object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_three.text.length>0){
                        field_four.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }
        )


        var code = intent.getStringExtra("code").toString()
        var usr = intent.getSerializableExtra("user") as User

        btn_confirm_signup.setOnClickListener {

            var sb = StringBuffer()
            sb.append(field_one.text.toString())
            sb.append(field_two.text.toString())
            sb.append(field_three.text.toString())
            sb.append(field_four.text.toString())
            var res = sb.toString()
            if (res.equals(code)) {

                val result = Post(usrURL(usr))
                if(!result.equals("null")){
                    Toast.makeText(applicationContext,"Inscription réussie ! vous pouvez à présent vous connecter", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Erreur lors de l'inscription", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"CODE incorrect !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun Post(url: String) : String {

        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() } }
        catch(e: Exception){
            System.out.println(e)
        }
        if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
            return x.toString()

        }
        return "null"
    }

    fun usrURL(usr: User) : String {

        var sb = StringBuffer()

        sb.append("http://scirusiwatch.herokuapp.com//addUser/")
        sb.append(usr.email+"/")
        sb.append(usr.jeton.toString()+"/")
        sb.append(usr.login+"/")
        sb.append(usr.lastName+"/")
        sb.append(usr.password+"/")
        sb.append(usr.picture+"/")
        sb.append(usr.firstName+"/")
        sb.append(usr.mobile+"/")
        sb.append(usr.adresse)

        val url = sb.toString()
        return url
    }
}
