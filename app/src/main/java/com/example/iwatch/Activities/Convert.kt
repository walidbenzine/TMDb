package com.example.iwatch.Activities

import com.example.iwatch.Entities.Comment
import com.example.iwatch.Entities.Serie
import com.example.iwatch.Entities.User
import com.example.iwatch.Entities.*
import com.example.iwatch.Enumerations.GenreType
import fr.upem.myapplication.CommentType
import org.json.JSONArray
import fr.upem.myapplication.Film
import org.json.JSONObject
import java.lang.Exception


class Convert {

    fun toUser(obj: JSONObject) : User {

        var usr =  User()
        usr.id = obj.get("id").toString().toInt()
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
        val genres = ArrayList<Genre>()
        val comment = ArrayList<Comment>()

        serie.id = obj.get("id").toString().toInt()
        serie.title = obj.get("title").toString()
        serie.resume = obj.get("resume").toString()
        serie.dateSortie = obj.get("dateSortie").toString()
        serie.note = obj.get("note").toString()
        serie.nbrEpisodes = obj.get("nbrEpisodes").toString().toInt()
        serie.nbrSaison = obj.get("nbrSaison").toString().toInt()
        serie.picture = obj.get("image").toString()

        var genrearray = JSONArray(obj.get("genre").toString())
        for( i in 0 until genrearray.length()){
            try {
                genres.add(Genre(GenreType.valueOf(JSONObject(genrearray.get(i).toString()).get("desig").toString())))
            }catch(e: Exception){
                System.out.println(e)
            }
        }
        serie.genreList = genres

        var commentarray = JSONArray(obj.get("comment").toString())
        for( i in 0 until commentarray.length()){
            try {
                comment.add(Comment(CommentType.valueOf((commentarray.get(i).toString()))))
            }catch(e: Exception){
                System.out.println(e)
            }
        }
        serie.commentList = comment

        return serie
    }


    fun toFilm(obj: JSONObject) : Film {

        val film =  Film ()
        val genres = ArrayList<Genre>()
        val comment = ArrayList<Comment>()

        film.id = obj.get("id").toString().toInt()
        film.dateSortie= obj.get("dateSortie").toString()
        film.duration = obj.get("title").toString()
        film.note = obj.get("note").toString()
        film.title = obj.get("dateSortie").toString()
        film.resume = obj.get("resume").toString()

        var genrearray = JSONArray(obj.get("genre").toString())
        for( i in 0 until genrearray.length()){
            try {
                genres.add(Genre(GenreType.valueOf(JSONObject(genrearray.get(i).toString()).get("desig").toString())))
            }catch(e: Exception){
                System.out.println(e)
            }
        }
        film.genre = genres

        var commentarray = JSONArray(obj.get("comment").toString())
        for( i in 0 until commentarray.length()){
            try {
                comment.add(Comment(CommentType.valueOf((commentarray.get(i).toString()))))
            }catch(e: Exception){
                System.out.println(e)
            }
        }
        film.comments = comment
        return film
    }

    fun toActor(obj: JSONObject) : Actor {

        var act =  Actor()
        act.id = obj.get("id").toString().toInt()
        act.lastName = obj.get("nom").toString()
        act.firstName = obj.get("nom").toString()
        act.cityOfBirth = obj.get("lieuNaissance").toString()
        act.bibliography = obj.get("bibliographie").toString()
        act.dateOfBirth = obj.get("dateNaissance").toString()
        act.popularity = obj.get("popularite").toString()
        act.picture = obj.get("photo").toString()

        return act
    }

    fun toSaison(obj: JSONObject) : Saison {

        var sais =  Saison()
        var episodes = ArrayList<Episode>()

        sais.id = obj.get("id").toString().toInt()
        sais.name = obj.get("nom").toString()
        sais.resume = obj.get("details").toString()
        sais.releasedDate = obj.get("dateSortie").toString()
        sais.nbrEpisode = obj.get("nbrEpisodes").toString().toInt()

        var episodearray = JSONArray(obj.get("listEpisodes").toString())
        for( i in 0 until episodearray.length()){
            try {
                episodes.add(toEpisode(JSONObject(episodearray.get(i).toString())))
            }catch(e: Exception){
                System.out.println(e)
            }
        }
        sais.episodeList = episodes

        return sais
    }


    fun toEpisode(obj: JSONObject) : Episode {

        var ep =  Episode()

        ep.id = obj.get("id").toString().toInt()
        ep.name = obj.get("Name").toString()
        ep.resume = obj.get("resume").toString()
        ep.dateDiffusion = obj.get("Date_Diff").toString()
        ep.number = obj.get("number").toString()
        ep.picture = obj.get("image").toString()
        ep.trailer = obj.get("bandeAnnonce").toString()

        return ep
    }


}