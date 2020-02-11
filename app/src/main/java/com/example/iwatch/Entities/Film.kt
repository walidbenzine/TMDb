package fr.upem.myapplication

import java.sql.Time
import java.util.Date

class Film {

    private val title: String? = null
    private val resume: String? = null
    internal var note: Int = 0
    internal var dateSortie: Date? = null
    internal var duration: Time? = null
    internal var filmsLiees: List<Film>? = null
    internal var genre: List<String>? = null  //PEUT ETRE A CHANGER
    internal var actors: List<Actors>? = null
    internal var realisator: List<String>? = null
    internal var comments: List<Comment>? = null

}
