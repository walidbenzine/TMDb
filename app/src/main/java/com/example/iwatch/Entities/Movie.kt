package fr.upem.myapplication

import java.sql.Time
import java.util.Date

class Movie {

    private val title: String? = null
    private val resume: String? = null
    internal var grade: Int = 0
    internal var releasedDate: Date? = null
    internal var duration: Time? = null
    internal var associatedMovies: List<Movie>? = null
    internal var genre: List<String>? = null  //PEUT ETRE A CHANGER
    internal var actors: List<Actor>? = null
    internal var realisator: List<String>? = null
    internal var comments: List<Comment>? = null

}
