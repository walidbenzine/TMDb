package com.example.iwatch.Entities

import java.io.Serializable


class Actor: Serializable{

    internal var id: Int = 0
    internal var firstName: String? = null
    internal var lastName: String? = null
    internal var cityOfBirth: String? = null
    internal var bibliography: String? = null
    internal var popularity: String? = null
    internal var dateOfBirth: String? = null
    internal var picture: String? = null

    internal var filmography = ArrayList<Movie>()

}
