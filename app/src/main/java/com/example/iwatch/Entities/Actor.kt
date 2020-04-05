package com.example.iwatch.Entities

import java.util.Date

class Actor {

    private val firstName: String? = null
    private val lastName: String? = null
    private val cityOfBirth: String? = null
    private val bibliography: String? = null
    internal var popularity: Int = 0
    internal var dateOfBirth: Date? = null
    internal var filmography: List<Movie>? = null
    internal var picture: String? = null //TYPE DE VARIABLE A CHANGER
}
