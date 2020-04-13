package com.example.iwatch.Entities
import com.example.iwatch.Entities.Actor
import com.example.iwatch.Entities.Comment
import com.example.iwatch.Entities.Genre
import java.io.Serializable
import java.sql.Time
import java.util.Date

class Movie (): Serializable {


    internal var id: Int = 0
    internal var imgFilm: String? = null
    internal var title: String? = null
    internal var resume: String? = null
    internal var note: String? = null
    internal var dateSortie: String? = null
    internal var genre: ArrayList<Genre>? = null
    internal var filmsLiees: List<Movie>? = null
    internal var actors: List<Actor>? = null
    internal var realisator: List<String>? = null
    internal var comments: List<Comment>? = null

}
