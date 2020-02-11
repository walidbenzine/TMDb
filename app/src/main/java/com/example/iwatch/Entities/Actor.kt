package fr.upem.myapplication

import java.util.Date

class Actor {

    private val nom: String? = null
    private val prenom: String? = null
    private val lieuNaissance: String? = null
    private val bibliographie: String? = null
    internal var popularite: Int = 0
    internal var dateNaissance: Date? = null
    internal var filmographie: List<Film>? = null
    internal var photo: String? = null //TYPE DE VARIABLE A CHANGER
}
