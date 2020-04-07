package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class MovieRoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val movieRoomPicture = itemView.findViewById<ImageView>(R.id.movie_room_picture) as ImageView
    val movieRoomName = itemView.findViewById<TextView>(R.id.movie_room_name) as TextView
    val movieRoomAddress = itemView.findViewById<TextView>(R.id.movie_room_address) as TextView

}