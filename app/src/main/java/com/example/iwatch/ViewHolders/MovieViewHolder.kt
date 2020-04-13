package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val movietitle = itemView.findViewById<TextView>(R.id.movie_title) as TextView
    val movieresume = itemView.findViewById<TextView>(R.id.movie_resume) as TextView
    val moviereleazeddate = itemView.findViewById<TextView>(R.id.movie_released_date) as TextView
    val moviepicture = itemView.findViewById<ImageView>(R.id.movie_picture) as ImageView
    //Button

}