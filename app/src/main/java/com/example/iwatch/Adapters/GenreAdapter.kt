package com.example.iwatch.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.Genre
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.GenreViewHolder
import kotlinx.android.synthetic.main.profile_genre_item.view.*



class GenreAdapter(val genreList: ArrayList<Genre>): RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.profile_genre_item, parent, false)
        return GenreViewHolder(v)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre: Genre = genreList[position]

        holder.genre.text = genre.genreType.toString()
        holder.genreDescription.text = genre.toast
        holder.genreIcon.setImageResource(genre.iconId!!)
    }
    /*private val mContext: Context? = context

    private val genres = arrayListOf<String>(
        "Adventure",
        "Action",
        "Drama",
        "Romance",
        "Thriller"
    )

    private val icons = arrayListOf<Int>(
        R.mipmap.ic_compass,
        R.mipmap.ic_gun,
        R.mipmap.ic_drama,
        R.mipmap.ic_heart,
        R.mipmap.ic_ghost
    )
    //rendering out each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val LayoutInflater = LayoutInflater.from(mContext)
        val genreItem = LayoutInflater.inflate(R.layout.profile_genre_item, parent, false)

        genreItem.genre.text = genres.get(position)
        genreItem.genre_decription.text = "find all movies"
        genreItem.genre_icon.setImageResource(icons.get(position))
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
    }*/
}