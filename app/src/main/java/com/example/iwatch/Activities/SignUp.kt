package com.example.iwatch.Activities

import android.net.Uri
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwatch.Fragments.SignUp1
import com.example.iwatch.Fragments.SignUp2
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up1.*
import kotlinx.android.synthetic.main.fragment_sign_up1.email
import kotlinx.android.synthetic.main.fragment_sign_up1.pass
import kotlinx.android.synthetic.main.fragment_sign_up2.*
import org.json.JSONObject

class SignUp : AppCompatActivity() , SignUp1.OnFragmentInteractionListener, SignUp2.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val fragmentManager = supportFragmentManager
    private val signUp1Fragment = SignUp1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, signUp1Fragment)
        fragmentTransaction.commit()
        signup.setOnClickListener() {
            doAsync {
                conx(email.text as String, 0, username.text as String, fname.text as String,pass.text as String ,"null",lname.text as String,phone.text as Int)
            }
        }
    }



    private fun conx(email:String, jeton :Int, username:String, fname : String,pass:String, addresse:String, lname:String, phone:Int  ):String {
        val url =
            "http://10.0.2.2:8080/addUser/$email/$jeton/$username/$fname/$pass/$addresse/$lname/$phone"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        val bodystr = response.body().string() // this can be consumed only once
        return bodystr


    }
}