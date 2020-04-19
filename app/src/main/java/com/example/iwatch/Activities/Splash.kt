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
import androidx.constraintlayout.widget.ConstraintLayout
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception


class Splash : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000L
    private var id = ""
    private val PERMISSION_REQUEST_CODE = 1
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "Scirus-Y"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val  SharedPreferences = getSharedPreferences(PREF_NAME, this.PRIVATE_MODE)

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
        Handler().postDelayed(
            {
          if (SharedPreferences.getString("login", "login").equals("login")) {
              val i = Intent(this, MainActivity::class.java)
              startActivity(i)
              finish()

          } else {
          connect()
        }
            }, SPLASH_TIME_OUT)

    }


    fun connect(){
        val  SharedPreferences = getSharedPreferences(PREF_NAME, this.PRIVATE_MODE)
        val homeIntent = Intent(this, Home::class.java)
        val i = Intent(this, MainActivity::class.java)
        val convert = Convert()

            var login = SharedPreferences.getString("login","")
            val password = SharedPreferences.getString("password","")

            if (!login.isNullOrEmpty() && !password.isNullOrEmpty()) {

                try{
                    doAsync {
                        var userJson = post.PostArray(Base_URL+"getUser/" + login + "/" + password)
                        if (userJson.toString() != "{}" && userJson.toString() != "[]" ) {

                            val user = convert.toUser(userJson.getJSONObject(0))
                            user.FavoriteMovies = post.PostFilm(Base_URL+"getFavFilm/"+ user.id)
                            user.FavoriteSeries = post.PostSerie(Base_URL+"getFavSerie/"+ user.id)

                            uiThread {
                                homeIntent.putExtra("user", user)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent)
                                finish()
                            }

                        }else {
                            uiThread {
                                startActivity(i)
                                finish()
                            }
                        }
                    }
                } catch(e: Exception){
                    System.out.println(e)
                }
            }
    }
}
