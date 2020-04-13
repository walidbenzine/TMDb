package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Movie
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.MovieViewHolder

class MovieAdapter(
    val movieList: ArrayList<Movie>,
    val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie,itemClickListener)
    }

}

interface OnMovieClickListener{
    fun onItemClicked(movie: Movie)
}