package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class AssociatedFilmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val associatedFilmPicture = itemView.findViewById<ImageView>(R.id.movie_film_picture) as ImageView
    val associatedFilmName = itemView.findViewById<TextView>(R.id.movie_film_name) as TextView

}