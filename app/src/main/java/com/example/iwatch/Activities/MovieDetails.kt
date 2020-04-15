package com.example.iwatch.Activities

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.Movie
import com.example.iwatch.Fragments.CommentsFragment
import com.example.iwatch.Fragments.MovieDetailsFragment
import com.example.iwatch.Fragments.MovieRoomsFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.net.URL


private var movie: Movie? = null
private var conv = Convert()

class MovieDetails : AppCompatActivity(), MovieDetailsFragment.OnFragmentInteractionListener,
    MovieRoomsFragment.OnFragmentInteractionListener,
    CommentsFragment.OnFragmentInteractionListener {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //get movie
        movie = intent.getSerializableExtra("movie") as Movie

        //enable back button on the toolbar
        movie_detail_toolbar.title = movie?.title
        setSupportActionBar(movie_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        movie_view_pager.adapter = mSectionsPagerAdapter
        movie_view_pager.currentItem = 0

        movie_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(movie_tabs))
        movie_tabs.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                movie_view_pager
            )
        )

        btn_movie_favori.setOnClickListener {
            Post("https://scirusiwatch.herokuapp.com/addFavFilm/" + user.id + "/" + movie?.id)
            Toast.makeText(applicationContext, "Ajout résussi", Toast.LENGTH_SHORT).show()
            btn_movie_favori.isFavorite = true
        }

        //play movie trailer
        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.movie_trailer)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = movie?.video
                videoId?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })


        //print movie details
        movie_detail_title.text = movie?.title
        Picasso.get().load(movie?.imgFilm).into(movie_detail_picture)
        for (i in 0..movie!!.genre?.size!! - 1) {
            movie_detail_genre.text = movie!!.genre?.get(i)?.genreType.toString() + ", "
        }
        movie_detail_released_date.text = movie?.dateSortie
        movie_detail_resume.text = movie?.resume
        movie_rating_bar.rating = ((movie?.note + "F").toFloat())/2
        movie_detail_rate.text = (((movie?.note + "F").toFloat())/2).toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    MovieDetailsFragment.newInstance(
                        PostActor("https://scirusiwatch.herokuapp.com/getAct/" + movie?.id.toString()),
                        PostFilm("https://scirusiwatch.herokuapp.com/getLi/" + movie?.id.toString())
                    )
                }
                1 -> MovieRoomsFragment.newInstance(PostCinema("https://scirusiwatch.herokuapp.com/getRoom/" + movie?.id.toString()))
                2 -> {
                    CommentsFragment.newInstance(conv.PostComment("https://scirusiwatch.herokuapp.com/getC/" + movie?.id.toString()))
                }
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 3
        }

    }

    fun Post(url: String) {
        val x = try {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            System.out.println(e)
        }
    }
}


