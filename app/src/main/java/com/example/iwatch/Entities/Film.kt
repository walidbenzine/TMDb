package fr.upem.myapplication

import com.example.iwatch.Entities.Actor
import com.example.iwatch.Entities.Comment
import java.io.Serializable
import java.sql.Time
import java.util.Date

class Film : Serializable {

    private val title: String? = null
    private val resume: String? = null
    internal var note: Int = 0
    internal var dateSortie: Date? = null
    internal var duration: Time? = null
    internal var filmsLiees: List<Film>? = null
    internal var genre: List<String>? = null  //PEUT ETRE A CHANGER
    internal var actors: List<Actor>? = null
    internal var realisator: List<String>? = null
    internal var comments: List<Comment>? = null

}
