package com.example.iwatch.ViewHolders

import android.graphics.BitmapFactory
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnActorClickListener
import com.example.iwatch.Entities.Actor
import com.example.iwatch.R
import com.squareup.picasso.Picasso
import java.net.URL

class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val actorPicture = itemView.findViewById<ImageView>(R.id.actor_picture) as ImageView
    val actorName = itemView.findViewById<TextView>(R.id.actor_name) as TextView

    fun bind(actor: Actor, clickListener: OnActorClickListener){

        //Picasso.get().load(actor.picture).into(actorPicture)
        var url = URL(actor.picture)
        var image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        actorPicture.setImageBitmap(image)
        actorName.text = actor.lastName

        itemView.setOnClickListener {
            clickListener.onActorClicked(actor)
        }
    }
}