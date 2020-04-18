package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnEpisodeClickListener
import com.example.iwatch.Entities.Episode
import com.example.iwatch.R
import com.squareup.picasso.Picasso

class EpisodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {



    val title = itemView.findViewById(R.id.episode_title) as TextView
    val resume = itemView.findViewById(R.id.episode_resume) as TextView
    val releaseddate = itemView.findViewById(R.id.episode_released_date) as TextView
    val popularity = itemView.findViewById(R.id.episode_grade) as TextView
    val picture = itemView.findViewById(R.id.episode_picture) as ImageView

    fun bind(episode: Episode, clickListener: OnEpisodeClickListener){
        title.text = episode!!.name
        resume.text = episode!!.resume
        releaseddate.text= episode!!.dateDiffusion
        popularity.text = episode!!.popularity?.substring(0,3)
        Picasso.get().load(episode!!.picture).into(picture)
        itemView.setOnClickListener {
            clickListener.onEpisodeClicked(episode)
        }
    }
}