package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iwatch.Entities.User
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_confirm_registration.*
import org.json.JSONArray
import java.net.URL


class ConfirmRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)

        var code = intent.getStringExtra("code").toString()
        var usr = intent.getSerializableExtra("user") as User

        signupconfirm.setOnClickListener {

            var sb = StringBuffer()
            sb.append(number1.text.toString())
            sb.append(number2.text.toString())
            sb.append(number3.text.toString())
            sb.append(number4.text.toString())
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

        //URL A CHANGER APRES HEBERGEMENT
        sb.append("http://10.0.2.2:8080/addUser/")
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
