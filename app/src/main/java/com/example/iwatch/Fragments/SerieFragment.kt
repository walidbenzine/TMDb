package com.example.iwatch.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Serie
import com.example.iwatch.R

class SerieFragment: Fragment()  {

    var serie = ArrayList<Serie>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_series, container, false)
        val genreRecyclerView = v.findViewById<RecyclerView>(R.id.recycleViewSeries)


        return v
    }
}

