package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Actor
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.ActorViewHolder

class ActorAdapter(val actorList: ArrayList<Actor>): RecyclerView.Adapter<ActorViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        return ActorViewHolder(v)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor: Actor = actorList[position]

        holder.actorName.text = actor.firstName + " " + actor.lastName
        holder.actorPicture.setImageResource(R.mipmap.ic_clown)
    }
}