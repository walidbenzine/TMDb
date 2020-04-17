package com.example.iwatch.Activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var id = ""
    val PERMISSION_REQUEST_CODE = 1
    var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it")
                val permissions = arrayOf(Manifest.permission.SEND_SMS)
                requestPermissions(permissions, PERMISSION_REQUEST_CODE)
            }
        }

        val convert = Convert()

        val btnSignUp = findViewById<View>(R.id.btn_signUp) as Button
        btnSignUp.setOnClickListener() {
            var signUpIntent = Intent(this@MainActivity, SignUp::class.java)
            startActivity(signUpIntent)
        }

        btn_login.setOnClickListener {
            var login = email.text.toString()
            val password = pass.text

            if (!login.isNullOrEmpty() && !password.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Connexion en cours", Toast.LENGTH_SHORT).show()
                try{
                var userJson = JSONArray()

                    val homeIntent = Intent(this, Home::class.java)
                    doAsync {
                        userJson = post.PostArray("http://scirusiwatch.herokuapp.com/getUser/" + login + "/" + password)
                        if (userJson.toString() != "{}" && userJson.toString() != "[]" ) {

                            val user = convert.toUser(userJson.getJSONObject(0))
                            user.FavoriteMovies = post.PostFilm("http://scirusiwatch.herokuapp.com/getFavFilm/"+ user.id)
                            user.FavoriteSeries = post.PostSerie("http://scirusiwatch.herokuapp.com//getFavSerie/"+ user.id)

                            uiThread {
                                Toast.makeText(applicationContext, "Connexion réussi", Toast.LENGTH_SHORT).show()
                                homeIntent.putExtra("user", user)
                                startActivity(homeIntent)
                            }

                    }else {
                            Toast.makeText(
                                    applicationContext,
                                    "Identifiants incorrects",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch(e: Exception){
                    System.out.println(e)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Entrez de bonnes valeurs SVP",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}