package com.example.iwatch.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwatch.Fragments.SignUp1
import com.example.iwatch.R

class SignUp : AppCompatActivity()  {

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
