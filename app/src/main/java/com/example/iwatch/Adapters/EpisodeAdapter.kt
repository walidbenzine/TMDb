package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Episode
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.EpisodeViewHolder

class EpisodeAdapter(
    val episodelist: ArrayList<Episode>,  val itemClickListener: OnEpisodeClickListener) : RecyclerView.Adapter<EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return EpisodeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return episodelist.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode: Episode = episodelist[position]
        holder.bind(episode, itemClickListener)
    }
}

interface OnEpisodeClickListener{
    fun onEpisodeClicked(episode: Episode)
}