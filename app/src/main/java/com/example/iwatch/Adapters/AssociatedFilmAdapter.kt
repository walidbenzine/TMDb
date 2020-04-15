package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Movie
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.AssociatedFilmViewHolder

class AssociatedFilmAdapter(
    val associatedFilmList: ArrayList<Movie>,
    val itemClickListener: OnAssociatedFilmClickListener
) : RecyclerView.Adapter<AssociatedFilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociatedFilmViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_associated_film_item, parent, false)
        return AssociatedFilmViewHolder(v)
    }

    override fun getItemCount(): Int {
        return associatedFilmList.size
    }

    override fun onBindViewHolder(holder: AssociatedFilmViewHolder, position: Int) {
        val associateFilm: Movie = associatedFilmList[position]
        holder.bind(associateFilm, itemClickListener)

    }
}

interface OnAssociatedFilmClickListener {
    fun onAssociatedMovieClicked(movie: Movie)
}