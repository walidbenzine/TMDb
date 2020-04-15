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
    internal var picture: String? = null //TYPE DE VARIABLE A CHANGER

    internal var FavoriteMovies: List<Movie>? = null  //A CHANGER
    internal var MovieHistory: List<Movie>? = null  //peut etre
    internal var FavoriteSeries: List<Serie>? = null //en hashmap
    internal var SerieHistory: List<Serie>? = null  //pour les favoris
    internal var genrePref = ArrayList<Genre>()  //PEUT ETRE A CHANGER


}
