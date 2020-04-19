package com.example.iwatch.Activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.view.View
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.iwatch.Entities.User
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.fragment_comments.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception
import java.net.URL

val Base_URL = "http://iwatchscirus.herokuapp.com/"

class MainActivity : AppCompatActivity() {

    var id = ""
    val PERMISSION_REQUEST_CODE = 1
    var mHandler: Handler? = null
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "Scirus-Y"
    val convert = Convert()
    lateinit var commentView: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val homeIntent = Intent(this, Home::class.java)

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


        val btnSignUp = findViewById<View>(R.id.btn_signUp) as TextView
        btnSignUp.setOnClickListener() {
            var signUpIntent = Intent(this@MainActivity, SignUp::class.java)
            startActivity(signUpIntent)
        }


        btn_login.setOnClickListener {
            var login = email.text.toString()
            val password = pass.text.toString()

            if (!login.isNullOrEmpty() && !password.isNullOrEmpty()) {
                try{
                    doAsync {
                        var userJson = post.PostArray(Base_URL+"getUser/" + login + "/" + password)
                        if (userJson.toString() != "{}" && userJson.toString() != "[]" ) {

                            val user = convert.toUser(userJson.getJSONObject(0))
                            user.FavoriteMovies = post.PostFilm(Base_URL+"getFavFilm/"+ user.id)
                            user.FavoriteSeries = post.PostSerie(Base_URL+"getFavSerie/"+ user.id)

                            uiThread {
                                Toast.makeText(applicationContext, "Connexion réussi", Toast.LENGTH_SHORT).show()
                                homeIntent.putExtra("user", user)

                                val  SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
                                var editor = SharedPreferences.edit()
                                editor.putString("login",login)
                                editor.putString("password",password.toString())
                                editor.commit()
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

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

