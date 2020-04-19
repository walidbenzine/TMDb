package com.example.iwatch.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_confirm_registration.*
import android.widget.Toast
import com.example.iwatch.Entities.User
import org.jetbrains.anko.doAsync
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

class ConfirmRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)


        val post = PostClass()
        val conv = Convert()

        field_one.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_one.text.length > 0) {
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
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_two.text.length > 0) {
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
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_three.text.length > 0) {
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


        var code = intent.getStringExtra("code").toString()
        var usr = intent.getSerializableExtra("user") as User

        usr.picture = encodeValue(encodeImage(usr.picture))

        btn_confirm_signup.setOnClickListener {

            var sb = StringBuffer()
            sb.append(field_one.text.toString())
            sb.append(field_two.text.toString())
            sb.append(field_three.text.toString())
            sb.append(field_four.text.toString())
            var res = sb.toString()
            if (res.equals(code)) {
                val result = post.Post(conv.usrURL(usr))
                System.out.println(result+"yaaaw")
                if (!result.equals("null")) {
                    Toast.makeText(
                        applicationContext,
                        "Inscription réussie ! vous pouvez à présent vous connecter",
                        Toast.LENGTH_LONG
                    ).show()
                    var ress = post.PostInt("http://scirusiwatch.herokuapp.com/maxid")
                    for (i in 0 until usr.genrePref!!.size) {
                        doAsync {
                            post.PostVoid(
                                "http://scirusiwatch.herokuapp.com/addgenreuser/${ress}/${usr.genrePref?.get(i)?.id}"
                            )

                        }

                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Erreur lors de l'inscription",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(applicationContext, "CODE incorrect !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun encodeImage(imagePath: String): String{

        val bytes = File(imagePath).readBytes()
        val base64 = Base64.getEncoder().encodeToString(bytes)

        return base64
    }

    fun encodeValue(value: String) : String{
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (ex: UnsupportedEncodingException) {
            throw RuntimeException(ex.cause)
        }
    }
}
