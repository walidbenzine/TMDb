package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.iwatch.ViewHolders.SeasonViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Saison
import com.example.iwatch.R
import com.squareup.picasso.Picasso


class SeasonAdapter (val seasonList: ArrayList<Saison>): RecyclerView.Adapter<SeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.serie_season_item, parent, false)
        return SeasonViewHolder(v)
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season: Saison = seasonList[position]

        holder.serieSaisonTitre.text = season.name
        holder.serieSaisonDate.text = season.releasedDate
        holder.serieSaisonNbr.text = season.nbrEpisode.toString()
        Picasso.get().load(season.photo).into(holder.serieSaisonPicture)
    }
}