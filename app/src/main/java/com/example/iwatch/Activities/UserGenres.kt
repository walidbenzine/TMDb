package com.example.iwatch.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.GenreAdapter
import com.example.iwatch.Entities.Genre
import com.example.iwatch.Enumerations.GenreType
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_user_genres.*

class UserGenres : AppCompatActivity() {

    var genres = ArrayList<Genre>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_genres)

        genre_toolbar.title = "Genres"
        setSupportActionBar(genre_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val genreRecyclerView = findViewById<RecyclerView>(R.id.genre_recycler_view)

        genres.add(Genre(GenreType.Thriller))
        genres.add(Genre(GenreType.Action))
        genres.add(Genre(GenreType.Romance))
        genres.add(Genre(GenreType.Drama))

        genreRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = GenreAdapter(genres)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
