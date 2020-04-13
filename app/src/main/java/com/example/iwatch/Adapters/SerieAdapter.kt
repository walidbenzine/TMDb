package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Serie
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.SerieViewHolder
import com.squareup.picasso.Picasso


class SerieAdapter (val serieList: ArrayList<Serie>): RecyclerView.Adapter<SerieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        return SerieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return serieList.size
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie: Serie = serieList[position]

        holder.serietitle!!.text= serie.title
        holder.seriedetails!!.text =serie.resume
        holder.seriereleazeddate!!.text = serie.dateSortie
        holder.datesortie!!.text= serie.dateSortie
        var nre: Int =serie.nbrEpisodes
        if(serie.picture != null){
            val url = serie.picture
            Picasso.get().load(url).into(holder.seriepicture)

        }


    }

}