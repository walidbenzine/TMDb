package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnMovieClickListener
import com.example.iwatch.Entities.Movie
import com.example.iwatch.R
import com.squareup.picasso.Picasso

class MovieViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val movietTitle = itemView.findViewById<TextView>(R.id.movie_title) as TextView
    val movieResume = itemView.findViewById<TextView>(R.id.movie_resume) as TextView
    val movieReleasedDate = itemView.findViewById<TextView> (R.id.movie_released_date) as TextView
    val moviePicture = itemView.findViewById<ImageView>(R.id.movie_picture) as ImageView
    val movieRate = itemView.findViewById<TextView>(R.id.movie_grade) as TextView

    fun bind(movie: Movie, clickListener: OnMovieClickListener){
        movietTitle.text = movie.title
        movieResume.text = movie.resume
        movieReleasedDate.text = movie.dateSortie
        Picasso.get().load(movie.imgFilm).into(moviePicture)
        movieRate.text = (((movie.note + "F").toFloat())/2).toString().take(3)

        itemView.setOnClickListener {
            clickListener.onMovieClicked(movie)
        }
    }

}



