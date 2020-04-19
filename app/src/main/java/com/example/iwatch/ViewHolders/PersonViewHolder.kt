package com.example.iwatch.ViewHolders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.PersonAdapter
import com.example.iwatch.Entities.Actor
import com.example.iwatch.R
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.net.URL

class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    val name = itemView.findViewById<TextView>(R.id.person_name) as TextView
    val birthDay = itemView.findViewById<TextView>(R.id.person_birth_day) as TextView
    val birthPlace = itemView.findViewById<TextView>(R.id.person_birth_place) as TextView
    val popularity = itemView.findViewById<TextView>(R.id.person_grade) as TextView
    val picture = itemView.findViewById<ImageView>(R.id.person_picture) as ImageView

    fun bind(actor: Actor, clickListener: PersonAdapter.OnPersonClickListener){
        name.text = actor.lastName
        birthDay.text = actor.dateOfBirth
        birthPlace.text = actor.cityOfBirth
        popularity.text = actor.popularity?.substring(0,3)
        //Picasso.get().load(actor.picture).into(picture)
        try{
            var url = URL(actor.picture)
            var image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            picture.setImageBitmap(image)
        }catch (e:Exception){

        }

        itemView.setOnClickListener {
            clickListener.onPersonClicked(actor)
        }
    }


}