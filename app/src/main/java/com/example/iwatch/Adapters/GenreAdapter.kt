package com.example.iwatch.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.iwatch.R
import kotlinx.android.synthetic.main.profile_genre_item.view.*

class GenreAdapter(context: Context): BaseAdapter() {
    private val mContext: Context = context

    private val genres = arrayListOf<String>(
        "Adventure",
        "Action",
        "Drama",
        "Romance",
        "Thriller"
    )

    //rendering out each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val LayoutInflater = LayoutInflater.from(mContext)
        val genreItem = LayoutInflater.inflate(R.layout.profile_genre_item, parent, false)

        genreItem.genre.text = genres.get(position)
        genreItem.genre_decription.text = "find all movies"
        return genreItem
    }

    override fun getItem(position: Int): Any {
        return "Test String"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //number of rows in listView
    override fun getCount(): Int {
        return 5
    }
}