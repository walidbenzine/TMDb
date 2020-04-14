package com.example.iwatch.ViewHolders

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnSerieClickListener
import com.example.iwatch.Entities.Serie
import com.example.iwatch.R
import com.squareup.picasso.Picasso

class SerieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val serietitle = itemView.findViewById(R.id.serie_title) as TextView
        val seriedetails = itemView.findViewById(R.id.serie_details) as TextView
        val datesortie = itemView.findViewById(R.id.date_sortie) as TextView
        val seriereleazeddate = itemView.findViewById(R.id.serie_released_date) as TextView
        val seriepicture = itemView.findViewById(R.id.serie_picture) as ImageView
        val serierate = itemView.findViewById(R.id.serie_grade) as TextView

        fun bind(serie: Serie, clickListener: OnSerieClickListener){
            serietitle!!.text = serie.title

            seriedetails!!.text =serie.resume
            seriereleazeddate!!.text= serie.dateSortie
            serierate!!.text = serie.note

            if(serie.picture != null){
                val url = serie.picture
                Picasso.get().load(url).into(seriepicture)

            }


            itemView.setOnClickListener {
                clickListener.onSerieClicked(serie)
            }

    }


}