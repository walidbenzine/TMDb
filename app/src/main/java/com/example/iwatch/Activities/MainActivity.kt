package com.example.iwatch.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwatch.Adapters.GenreAdapter
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_profile.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        genre_list.adapter = GenreAdapter(this)
    }


}
