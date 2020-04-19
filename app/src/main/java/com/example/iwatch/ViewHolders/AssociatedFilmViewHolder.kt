package com.example.iwatch.ViewHolders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnAssociatedFilmClickListener
import com.example.iwatch.Entities.Movie
import com.example.iwatch.R
import com.squareup.picasso.Picasso
import java.net.URL

class AssociatedFilmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val associatedFilmPicture = itemView.findViewById<ImageView>(R.id.movie_film_picture) as ImageView
    val associatedFilmName = itemView.findViewById<TextView>(R.id.movie_film_name) as TextView

    fun bind(movie: Movie, clickListener: OnAssociatedFilmClickListener){
        associatedFilmName.text = movie.title
        //Picasso.get().load(movie.imgFilm).into(associatedFilmPicture)
        var url = URL(movie.imgFilm)
        var image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        associatedFilmPicture.setImageBitmap(image)

        itemView.setOnClickListener {
            clickListener.onAssociatedMovieClicked(movie)
        }
    }

}