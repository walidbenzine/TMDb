package com.example.iwatch.Entities
import android.widget.ImageView
import com.example.iwatch.Enumerations.GenreType
import com.example.iwatch.R

data class Genre (var genreType: GenreType) {

    var toast:String? = null
    var iconId:Int? = null

    init {
        when(genreType){
            GenreType.Action -> {
                toast = "Let catch bad boys"
                iconId = R.mipmap.ic_gun
            }
            GenreType.Adventure -> {
                toast = "Life is an adventure"
                iconId = R.mipmap.ic_compass
            }
            GenreType.Comedy -> {
                toast = "Let's have fun tonight"
                iconId = R.mipmap.ic_clown
            }
            GenreType.Drama -> {
                toast = "a story about who?"
                iconId = R.mipmap.ic_drama
            }
            GenreType.Romance -> {
                toast = "Will they get married"
                iconId = R.mipmap.ic_heart
            }
            GenreType.Thriller -> {
                toast = "who will be killed"
                iconId = R.mipmap.ic_ghost
            }
        }

    }


}