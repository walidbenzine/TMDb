package com.example.iwatch.Entities

import fr.upem.myapplication.CommentType
import java.util.*


class Comment(var text: String) {


    internal var toast: String? = null
    internal var commentType: CommentType = CommentType.Episode
    private val type: String? = null
    internal var publishedDate: Date? = null
    internal var idUser: Int = 0
    internal var idProd: Int = 0


    init {
        when(commentType){
            CommentType.Movie -> {
                toast = "You comment a film"

            }
           CommentType.Episode -> {
                toast = "You comment an episode"

            }
           CommentType.Season -> {
                toast = "You comment a saison"

            }
           CommentType.Tv -> {
                toast = "You comment a serie"

            }

            CommentType.nulle -> {
                toast = "no comment"

            }


        }

    }
}
