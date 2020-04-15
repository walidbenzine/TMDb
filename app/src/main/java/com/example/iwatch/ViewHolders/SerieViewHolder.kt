package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnSerieClickListener
import com.example.iwatch.Entities.Serie
import com.example.iwatch.R
import com.squareup.picasso.Picasso

class SerieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val serieTitle = itemView.findViewById(R.id.serie_title) as TextView
    val serieDetails = itemView.findViewById(R.id.serie_details) as TextView
    val serieReleazedDate = itemView.findViewById(R.id.serie_released_date) as TextView
    val seriePicture = itemView.findViewById(R.id.serie_picture) as ImageView
    val serieRate = itemView.findViewById(R.id.serie_grade) as TextView

    fun bind(serie: Serie, clickListener: OnSerieClickListener){

        Picasso.get().load(serie?.picture).into(seriePicture)
        serieTitle.text = serie.title
        serieDetails.text =serie.resume
        serieReleazedDate!!.text= serie.dateSortie
        serieRate.text = (((serie.note + "F").toFloat())/2).toString().take(3)

        itemView.setOnClickListener {
            clickListener.onSerieClicked(serie)
        }

    }


}