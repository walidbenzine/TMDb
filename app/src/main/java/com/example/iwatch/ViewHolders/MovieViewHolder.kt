package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    var movietitle: TextView?=null
    var movieresume: TextView?=null
    var datesortie: TextView?=null
    var moviepicture: ImageView?=null

    init {
        movietitle = itemView.findViewById(R.id.movie_title) as TextView
        movieresume = itemView.findViewById(R.id.movie_resume) as TextView
        datesortie= itemView.findViewById (R.id.date_sortie) as TextView
        moviepicture = itemView.findViewById(R.id.movie_picture) as ImageView
    //var pic = itemView.findViewById(R.id.serie_picture) as ImageView

    }
}