package com.example.iwatch.Activities

import com.example.iwatch.Entities.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class PostClass {

    val conv = Convert()

    fun PostComment(url: String): ArrayList<Comment> {
        var arrayComment = ArrayList<Comment>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        try {
            if (!x.toString().isNullOrEmpty() && x.toString() != "null" && !x.toString().equals("[]")) {
                var jsonarray = JSONArray(x.toString())
                for (i in 0 until jsonarray.length()) {
                    arrayComment.add(conv.toComment(JSONObject(jsonarray.get(i).toString())))
                }
                return arrayComment
            }
        } catch (e: Exception) {
            System.out.println(e)
            return arrayComment
        }
        return arrayComment
    }


    fun Post(url: String) : String {

        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() } }
        catch(e: Exception){
            System.out.println(e)
        }
        if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
            return x.toString()

        }
        return "null"
    }

    fun PostInt(url: String) : Int {

        val x = try {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
        catch(e: Exception){
            System.out.println(e)
        }
        if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
            System.out.println(x.toString())
            System.out.println(x.toString().toInt())
            return x.toString().toInt()

        }
        return 0
    }


    fun PostVoid(url: String) {
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
    }


    fun PostArray(url: String): JSONArray {

        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        try {
            if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
                return JSONArray(x.toString())

            }
            return JSONArray()
        }catch(e: Exception){
            System.out.println(e)
            return JSONArray()
        }
    }

    fun PostObject(url: String): JSONObject {

        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        try {
            if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
                return JSONObject(x.toString())

            }
            return JSONObject()
        }catch(e: Exception){
            System.out.println(e)
            return JSONObject()
        }
    }

    fun PostCinema(url: String): ArrayList<Cinema> {
        var arrayCinema = ArrayList<Cinema>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        try{
            if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
                var jsonarray = JSONArray(x.toString())
                for (i in 0 until jsonarray.length()) {
                    arrayCinema.add(convert.toCinema(JSONObject(jsonarray.get(i).toString())))
                }
                return arrayCinema
            }else{
                return arrayCinema
            }
        }catch (e: Exception){
            System.out.println(e)
        }
        return arrayCinema
    }

    fun PostSaison(url: String): ArrayList<Saison> {
        var arraySaison = ArrayList<Saison>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
            var jsonarray = JSONArray(x.toString())
            for (i in 0 until jsonarray.length()) {
                arraySaison.add(convert.toSaison(JSONObject(jsonarray.get(i).toString())))
            }
            return arraySaison
        }
        return arraySaison
    }

    fun PostActor(url: String): ArrayList<Actor> {
        var arrayactors = ArrayList<Actor>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        try {
            if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
                var jsonarray = JSONArray(x.toString())
                for (i in 0 until jsonarray.length()) {
                    arrayactors.add(convert.toActor(JSONObject(jsonarray.get(i).toString())))
                }
                return arrayactors
            }
            return arrayactors
        }catch(e: Exception) {
            System.out.println(e)
            return arrayactors
        }
    }

    fun PostFilm(url: String): ArrayList<Movie> {
        var arrayfilms = ArrayList<Movie>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
            var jsonarray = JSONArray(x.toString())
            for (i in 0 until jsonarray.length()) {
                arrayfilms.add(convert.toFilm(JSONObject(jsonarray.get(i).toString())))
            }
            return arrayfilms
        }
        return arrayfilms
    }

    fun PostSerie(url: String): ArrayList<Serie> {
        var arrayseries = ArrayList<Serie>()
        val x = try {
            URL(url)
                    .openStream()
                    .bufferedReader()
                    .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
        if (!x.toString().isNullOrEmpty() && x.toString() != "null" && x.toString() != "[]") {
            var jsonarray = JSONArray(x.toString())
            for (i in 0 until jsonarray.length()) {
                arrayseries.add(convert.toSerie(JSONObject(jsonarray.get(i).toString())))
            }
            return arrayseries
        }
        return arrayseries
    }
}