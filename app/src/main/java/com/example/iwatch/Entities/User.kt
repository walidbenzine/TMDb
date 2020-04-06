package com.example.iwatch.Entities

import java.io.Serializable

class User :Serializable{

    internal var firstName: String? = null
    internal var lastName: String? = null
    internal var email: String? = null
    internal var password: String? = null
    internal var mobile: String? = null
    internal var jeton: Int = 0
    internal var picture: String? = null //TYPE DE VARIABLE A CHANGER

    internal var FavoriteMovies: List<Movie>? = null  //A CHANGER
    internal var MovieHistory: List<Movie>? = null  //peut etre
    internal var FavoriteSeries: List<Serie>? = null //en hashmap
    internal var SerieHistory: List<Serie>? = null  //pour les favoris
    internal var genrePref: List<String>? = null  //PEUT ETRE A CHANGER


}
