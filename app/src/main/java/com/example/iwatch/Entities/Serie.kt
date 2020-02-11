package fr.upem.myapplication

import java.util.Date

class Serie {

    private val title: String? = null
    private val resume: String? = null
    internal var dateSortie: Date? = null
    internal var note: Int = 0
    internal var nbrEpisodes: Int = 0
    internal var nbrSaison: Int = 0
    internal var saisons: List<Saison>? = null
    internal var seriesLiees: List<Serie>? = null
    internal var comments: List<Comment>? = null
    internal var genre: List<String>? = null //PEUT ETRE A CHANGER
}
