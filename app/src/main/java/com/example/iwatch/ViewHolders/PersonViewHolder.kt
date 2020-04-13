package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    val name = itemView.findViewById<TextView>(R.id.person_name) as TextView
    val birthDay = itemView.findViewById<TextView>(R.id.person_birth_day) as TextView
    val birthPlace = itemView.findViewById<TextView>(R.id.person_birth_place) as TextView
    val popularity = itemView.findViewById<TextView>(R.id.person_grade) as TextView
}