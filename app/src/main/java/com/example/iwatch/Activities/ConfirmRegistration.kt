package com.example.iwatch.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_confirm_registration.*




class ConfirmRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)

        var code = intent.getStringExtra("code").toString()

        signupconfirm.setOnClickListener {

            var sb = StringBuffer()
            sb.append(number1.text.toString())
            sb.append(number2.text.toString())
            sb.append(number3.text.toString())
            sb.append(number4.text.toString())
            var res = sb.toString()
            if (res.equals(code)) {

                Toast.makeText(applicationContext,"CODE OK", Toast.LENGTH_SHORT).show()

                /*
                DO REST OF STUFF HERE
                 */

            }

        }
    }
}
