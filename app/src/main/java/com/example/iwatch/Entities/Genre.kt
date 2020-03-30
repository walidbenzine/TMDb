package com.example.iwatch.Entities

import com.example.iwatch.Enumerations.GenreType
import kotlin.properties.Delegates

class Genre (genreType: GenreType) {
    private var genreType: GenreType by Delegates.notNull<GenreType>()
    private var toast: String? = null

    init {
        this.genreType = genreType
        when(genreType){
            GenreType.Action -> toast = "action"
            GenreType.Adventure -> toast = "adventure"
            GenreType.Comedy -> toast = "comedy"
            GenreType.Drama -> toast = "drama"
            GenreType.Romance -> toast = "romance"
            GenreType.Thriller -> toast = "thriller"
        }
    }
}