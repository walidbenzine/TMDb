package com.example.iwatch.ViewHolders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.SeasonAdapter
import com.example.iwatch.Entities.Saison
import com.example.iwatch.R
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.net.URL

class SeasonViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val serieSaisonTitre = itemView.findViewById<TextView>(R.id.serie_season_title) as TextView
    val serieSaisonDate = itemView.findViewById<TextView>(R.id.serie_season_released_date) as TextView
    val serieSaisonNbr = itemView.findViewById<TextView>(R.id.serie_season_episode_nbr) as TextView
    val serieSaisonPicture = itemView.findViewById<ImageView>(R.id.movie_picture) as ImageView


    fun bind(season: Saison, clickListener:  SeasonAdapter.OnSeasonClickListener  ){

        serieSaisonTitre.text = season!!.name
        serieSaisonDate.text = season!!.releasedDate
        serieSaisonNbr.text = season!!.nbrEpisode.toString()
        //Picasso.get().load(season!!.photo).into(serieSaisonPicture)
        var url = URL(season.photo)
        try {
            var image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            serieSaisonPicture.setImageBitmap(image)
        }catch(e: Exception){

        }

        itemView.setOnClickListener {
            clickListener.onSeasonClicked(season)
        }
    }

}