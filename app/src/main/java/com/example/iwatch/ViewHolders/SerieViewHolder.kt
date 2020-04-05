package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class SerieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val serietitle = itemView.findViewById<TextView>(R.id.serie_title) as TextView
    val seriedetails = itemView.findViewById<TextView>(R.id.serie_details) as TextView
    val seriereleazeddate = itemView.findViewById<TextView>(R.id.serie_released_date) as TextView
    val seriepicture = itemView.findViewById<ImageView>(R.id.serie_picture) as ImageView
    //val seriegrade = itemView.findViewById<ImageView>(R.id.serie_grade) as Button

}