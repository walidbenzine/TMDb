package fr.upem.myapplication

class User {

    private val firstName: String? = null
    private val lastName: String? = null
    private val email: String? = null
    private val password: String? = null
    private val mobile: String? = null

    internal var FavoriteMovies: List<Movie>? = null  //A CHANGER
    internal var MovieHistory: List<Movie>? = null  //peut etre
    internal var FavoriteSeries: List<Serie>? = null //en hashmap
    internal var SerieHistory: List<Serie>? = null  //pour les favoris

    internal var genrePref: List<String>? = null  //PEUT ETRE A CHANGER
    internal var jeton: Int = 0
    internal var picture: String? = null //TYPE DE VARIABLE A CHANGER

}
