package com.example.iwatch.Activities

import com.example.iwatch.Entities.Comment
import com.example.iwatch.Entities.Serie
import com.example.iwatch.Entities.User
import fr.upem.myapplication.Film
import org.json.JSONObject
import java.net.URL


class Convert {

    fun toUser(obj: JSONObject) : User {

        var usr =  User()
        usr.lastName = obj.get("nom").toString()
        usr.firstName = obj.get("prenom").toString()
        usr.email = obj.get("email").toString()
        usr.password = obj.get("password").toString()
        usr.mobile = obj.get("tel").toString()
        usr.jeton = obj.get("jeton").toString().toInt()
        usr.picture = obj.get("picture").toString()

        return usr
    }

    fun toSerie(obj: JSONObject) : Serie {

        val serie =  Serie ()
        serie.id = obj.get("id").toString().toInt()
        serie.title = obj.get("title").toString()
        serie.resume = obj.get("resume").toString()
        serie.dateSortie = obj.get("dateSortie").toString()
        serie.note = obj.get("note").toString()
        serie.nbrEpisodes = obj.get("nbrEpisodes").toString().toInt()
        serie.nbrSaison = obj.get("nbrSaison").toString().toInt()
        serie.picture = obj.get("image").toString()



        return serie
    }
    fun toFilm(obj: JSONObject) : Film {

        val film =  Film ()
        film.dateSortie= obj.get("dateSortie").toString()
        film.duration = obj.get("title").toString()
        film.note = obj.get("resume").toString().toInt()
        film.title = obj.get("dateSortie").toString()
        film.resume = obj.get("note").toString()



        return film
    }

    fun toComment(obj: JSONObject) : Comment {

        val comment =  Comment ()
        comment.



        return comment
    }
}