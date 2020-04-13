package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Cinema
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.CinemaRoomViewHolder
import com.squareup.picasso.Picasso

class CinemaRoomAdapter (val movieRoomList: ArrayList<Cinema>): RecyclerView.Adapter<CinemaRoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaRoomViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.cinema_room_item, parent, false)
        return CinemaRoomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieRoomList.size
    }

    override fun onBindViewHolder(holder: CinemaRoomViewHolder, position: Int) {
        val movieRoom: Cinema = movieRoomList[position]

        holder.movieRoomName.text = movieRoom.nom
        holder.movieRoomAddress.text = movieRoom.adresse
        Picasso.get().load(movieRoom.image).into(holder.movieRoomPicture)
    }
}