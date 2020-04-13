package com.example.iwatch.ViewHolders

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Activities.SerieDetails
import com.example.iwatch.R

class SerieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    var serietitle: TextView?=null
    var seriedetails: TextView?=null
    var datesortie: TextView?=null
    var seriereleazeddate: TextView?=null
    init {


        serietitle = itemView.findViewById(R.id.serie_title) as TextView
        seriedetails = itemView.findViewById(R.id.serie_details) as TextView
        datesortie = itemView.findViewById(R.id.date_sortie) as TextView
        seriereleazeddate = itemView.findViewById(R.id.serie_released_date) as TextView
        //seriepicture = itemView.findViewById(R.id.serie_picture) as ImageView

        itemView.setOnClickListener{
            val intent = Intent(itemView.context, SerieDetails::class.java)
            ContextCompat.startActivity(itemView.context, intent, null)
        }

    }


}