package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwatch.Fragments.SignUp1
import com.example.iwatch.Fragments.SignUp2
import com.example.iwatch.R

class SignUp : AppCompatActivity(), SignUp1.OnFragmentInteractionListener,
    SignUp2.OnFragmentInteractionListener{

    private val fragmentManager = supportFragmentManager
    private val signUp1Fragment = SignUp1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, signUp1Fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}