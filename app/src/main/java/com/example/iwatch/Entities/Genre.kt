package com.example.iwatch.Entities
import android.widget.ImageView
import com.example.iwatch.Enumerations.GenreType
import com.example.iwatch.R
import java.io.Serializable

data class Genre (var genreType: GenreType): Serializable{

    var toast:String? = null
    var iconId:Int? = null
    var id:Int=0
    init {
        when(genreType){
            GenreType.Action -> {
                toast = "Let's catch bad boys"
                iconId = R.mipmap.ic_gun
                id=28
            }
            GenreType.Adventure -> {
                toast = "Life is an adventure"
                iconId = R.mipmap.ic_compass
                id=12
            }
            GenreType.Comedy -> {
                toast = "Let's have fun tonight"
                iconId = R.mipmap.ic_clown
                id=35
            }
            GenreType.Drama -> {
                toast = "a story about who?"
                iconId = R.mipmap.ic_drama
                id=18
            }
            GenreType.Romance -> {
                toast = "Will they get married"
                iconId = R.mipmap.ic_heart
                id=10749
            }
            GenreType.Thriller -> {
                toast = "who will be killed"
                iconId = R.mipmap.ic_ghost
                id=53
            }
            GenreType.War -> {
                toast = "Let's fight today"
                iconId = R.mipmap.ic_ghost
                id=10752

            }
        }

    }


}