package fr.upem.myapplication

import com.example.iwatch.Entities.Actor
import com.example.iwatch.Entities.Comment
import com.example.iwatch.Entities.Genre
import java.io.Serializable
import java.sql.Time
import java.util.Date

class Film : Serializable {

    internal var id: Int = 0
    internal var title: String? = null
    internal var resume: String? = null
    internal var note: String? = null
    internal var dateSortie: String? = null
    internal var duration: String? = null
    internal var genre: ArrayList<Genre>? = null


    internal var filmsLiees: List<Film>? = null
    internal var actors: List<Actor>? = null
    internal var realisator: List<String>? = null
    internal var comments: List<Comment>? = null

}
