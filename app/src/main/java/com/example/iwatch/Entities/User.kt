package fr.upem.myapplication

class User {

    private val name: String? = null
    private val prenom: String? = null
    private val email: String? = null
    private val password: String? = null
    private val tel: String? = null

    internal var FilmFavoris: List<Film>? = null  //A CHANGER
    internal var Filmhistory: List<Film>? = null  //peut etre
    internal var SerieFavoris: List<Serie>? = null //en hashmap
    internal var Seriehistory: List<Serie>? = null  //pour les favoris

    internal var genrePref: List<String>? = null  //PEUT ETRE A CHANGER
    internal var jeton: Int = 0
    internal var picture: String? = null //TYPE DE VARIABLE A CHANGER

}
