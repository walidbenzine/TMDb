package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    var movietitle = itemView.findViewById<TextView>(R.id.movie_title) as TextView
    var movieresume = itemView.findViewById<TextView>(R.id.movie_resume) as TextView
    var datesortie= itemView.findViewById<TextView> (R.id.date_sortie) as TextView
    var moviereleazeddate = itemView.findViewById<TextView>(R.id.movie_released_date) as TextView
    var moviepicture = itemView.findViewById<ImageView>(R.id.movie_picture) as ImageView
    //var pic = itemView.findViewById(R.id.serie_picture) as ImageView

}