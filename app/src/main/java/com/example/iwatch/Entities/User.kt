package com.example.iwatch.Entities

import java.io.Serializable
import java.math.BigInteger

class User :Serializable{

    internal var id: Int? = null
    internal var firstName: String? = null
    internal var lastName: String? = null
    internal var email: String? = null
    internal var password: String? = null
    internal var mobile: String? = null
    internal var login: String? = null
    internal var adresse: String? = null
    internal var jeton: Int = 0
    internal var picture: String? = null

    internal var FavoriteMovies = ArrayList<Movie>()
    internal var MovieHistory: List<Movie>? = null
    internal var FavoriteSeries = ArrayList<Serie>()
    internal var SerieHistory: List<Serie>? = null
    internal var genrePref = ArrayList<Genre>()


}
