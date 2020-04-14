package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Actor
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.PersonViewHolder
import com.squareup.picasso.Picasso

class PersonAdapter(
    val actorList: ArrayList<Actor>,
    val personClickListener: OnPersonClickListener
) : RecyclerView.Adapter<PersonViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(v)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val actor: Actor = actorList[position]
        holder.bind(actor, personClickListener)
    }

    interface OnPersonClickListener {
        fun onPersonClicked(actor : Actor)
    }
}