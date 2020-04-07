package com.example.iwatch.Entities

import java.io.Serializable


class Actor(var firstName: String, var lastName: String): Serializable{

    internal var id: Int = 0
    internal var firstName: String? = null
    internal var lastName: String? = null
    internal var cityOfBirth: String? = null
    internal var bibliography: String? = null
    internal var popularity: String? = null
    internal var dateOfBirth: String? = null
    internal var picture: String? = null

    internal var filmography: List<Movie>? = null

    val cityOfBirth: String? = null
    val bibliography: String? = null
    internal var popularity: Int = 0
    internal var dateOfBirth: Date? = null
    internal var filmography: List<Movie>? = null
    internal var picture: String? = null 
}
