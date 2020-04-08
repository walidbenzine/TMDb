package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val convert = Convert()

        val btnSignUp = findViewById<View>(R.id.btn_signUp) as Button
        btnSignUp.setOnClickListener() {
            var signUpIntent = Intent(this@MainActivity, SignUp::class.java)
            startActivity(signUpIntent)
        }


        btn_login.setOnClickListener {

            val login = email.text
            val password = pass.text

            if(!login.isNullOrEmpty() && !password.isNullOrEmpty()) {

                val userJson = Post("http://10.0.2.2:8080/getUser/" + login + "/" + password)
                System.out.println(userJson)
                if (userJson.toString() != "{}" ) {
                    Toast.makeText(applicationContext,"Connexion résussi",Toast.LENGTH_SHORT).show()
                    val user = convert.toUser(userJson.getJSONObject(0))
                    val homeIntent = Intent(this, Home::class.java)
                    homeIntent.putExtra("user", user)
                    startActivity(homeIntent)
                }
                else{
                    Toast.makeText(applicationContext,"Identifiants incorrects",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext,"Entrez de bonnes valeurs SVP",Toast.LENGTH_SHORT).show()
            }
        }
        }


    fun Post(url: String) : JSONArray {

        val x = try {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
        catch(e: Exception){
            System.out.println(e)
        }
        if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
            return JSONArray(x.toString())

        }
        return JSONArray("{}")
    }
}