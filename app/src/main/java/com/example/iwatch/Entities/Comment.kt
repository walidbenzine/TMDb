package com.example.iwatch.Entities
import java.util.Date

class Comment(var text: String) {

    private val type: String? = null
    internal var publishedDate: Date? = null
    internal var idUser: Int = 0
    internal var idProd: Int = 0
}
