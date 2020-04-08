package com.example.iwatch.Activities

import android.net.Uri
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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
    }

}