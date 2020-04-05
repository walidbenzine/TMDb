package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iwatch.R
import java.io.IOException
import okhttp3.*
import android.os.StrictMode
import java.net.URL


class MainActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val buttonCnx = findViewById<Button>(R.id.ButtonCnx)
        val emailEditText = findViewById<EditText>(R.id.EmailEditText)
        val passEditText = findViewById<EditText>(R.id.passEditText)


        buttonCnx.setOnClickListener {

            val login = emailEditText.text
            val password = passEditText.text

            if(!login.isNullOrEmpty() && !password.isNullOrEmpty()) {

                val x = Post("http://10.0.2.2:8080/getUser/" + login + "/" + password)
                System.out.println("RESULT ============ " + x)

                if (!x.equals("[]") && !x.isNullOrEmpty()) {
                    Toast.makeText(applicationContext,"Connexion résussi",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
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

    fun Post(url: String) :String{

        val x = try {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
        catch(e: Exception){
            System.out.println(e)
        }
        return x.toString()
    }

}
