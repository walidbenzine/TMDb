package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val actorPicture = itemView.findViewById<TextView>(R.id.actor_picture) as ImageView
    val actorName = itemView.findViewById<TextView>(R.id.actor_name) as TextView
}