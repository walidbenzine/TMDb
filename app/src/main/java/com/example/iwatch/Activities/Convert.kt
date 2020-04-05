package com.example.iwatch.Activities

import com.example.iwatch.Entities.Serie
import com.example.iwatch.Entities.User
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
}