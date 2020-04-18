package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Episode
import com.example.iwatch.R
import com.squareup.picasso.Picasso

class EpisodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {



    val title = itemView.findViewById(R.id.episode_title) as TextView
    val resume = itemView.findViewById<TextView>(R.id.episode_resume) as TextView
    val releaseddate = itemView.findViewById<TextView>(R.id.episode_released_date) as TextView
    val picture = itemView.findViewById(R.id.episode_picture) as ImageView

    fun bind(episode: Episode, clickListener: EpisodeAdapter.OnPersonClickListener){
        title.text = episode.name
        resume.text = episode.resume
        releaseddate.text= episode.dateDiffusion

        Picasso.get().load(episode.picture).into(picture)
        itemView.setOnClickListener {
            clickListener.onPersonClicked(episode)
        }
    }
}