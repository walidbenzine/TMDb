package com.example.iwatch.Entities

import java.io.Serializable


class Saison : Serializable {

    internal var id: Int = 0
    internal var name: String? = null
    internal var resume: String? = null
    internal var releasedDate: String? = null
    internal var nbrEpisode: Int = 0
    internal var episodeList: ArrayList<Episode>? = null
    internal var photo: String? = null

    //A SUPPRIMER
    internal var actorList: List<Actor>? = null


}
