package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_confirm_registration.*

class ConfirmRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)

        var btnConfirmSignUp = findViewById<View>(R.id.btn_confirm_signup) as Button

        field_one.addTextChangedListener(
            object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_one.text.length>0){
                        field_two.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })

        field_two.addTextChangedListener(
            object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_two.text.length>0){
                        field_three.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

        field_three.addTextChangedListener(
            object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (field_three.text.length>0){
                        field_four.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }
        )

        btnConfirmSignUp.setOnClickListener {
            var homeIntent = Intent(this, Home::class.java)
            startActivity(homeIntent)
        }
    }
}
