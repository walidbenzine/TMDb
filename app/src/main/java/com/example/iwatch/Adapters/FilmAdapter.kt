package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.FilmViewHolder
import fr.upem.myapplication.Movie

class FilmAdapter (val movieList: ArrayList<Movie>): RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return FilmViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: Movie = movieList[position]

        holder.movietitle.text = film.title
        holder.movieresume.text = film.resume
        holder.moviereleazeddate.text = film.releasedDate
        holder.moviepicture.setImageResource(film.imgFilm)

    }
}