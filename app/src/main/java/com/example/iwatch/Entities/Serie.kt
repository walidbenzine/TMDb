package com.example.iwatch.Entities
import android.widget.ImageView
import java.util.Date

data class Serie (var title:String, var details:String, var img: Int)
{

    //private val title: String? = null
    private val resume: String? = null
    internal var releasedDate: String? = null
    internal var grade: Int = 0
    internal var nbrEpisodes: Int = 0
    internal var nbrSaison: Int = 0
    internal var saisonList: List<Saison>? = null
    internal var associatedSeries: List<Serie>? = null
    internal var commentList: List<Comment>? = null
    internal var genreList: List<String>? = null //PEUT ETRE A CHANGER
}
