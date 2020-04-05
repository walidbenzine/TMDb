package com.example.iwatch.ViewHolders


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val genre = itemView.findViewById<TextView>(R.id.genre) as TextView
    val genreDescription = itemView.findViewById<TextView>(R.id.genre_decription) as TextView
    val genreIcon = itemView.findViewById<ImageView>(R.id.genre_icon) as ImageView

}