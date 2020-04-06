package com.example.iwatch.Entities

import java.io.Serializable


class Serie: Serializable{

    internal var title: String? = null
    internal var id: Int = 0
    internal var resume: String? = null
    internal var dateSortie: String? = null
    internal var note: String? = null
    internal var nbrEpisodes: Int = 0
    internal var nbrSaison: Int = 0
    internal var picture: String? = null

    internal var saisonList: List<Saison>? = null
    internal var associatedSeries: List<Serie>? = null
    internal var commentList: List<Comment>? = null
    internal var genreList: List<String>? = null //PEUT ETRE A CHANGER
}
