package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.Saison
import com.example.iwatch.Entities.Serie
import com.example.iwatch.Fragments.AssociatedSeriesFragment
import com.example.iwatch.Fragments.CommentsFragment
import com.example.iwatch.Fragments.SeasonFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_serie_details.*

var serie = Serie()

class SerieDetails : AppCompatActivity(), SeasonFragment.OnFragmentInteractionListener,
    AssociatedSeriesFragment.OnFragmentInteractionListener, CommentsFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)

        serie = intent.getSerializableExtra("serie") as Serie

        user.FavoriteSeries = post.PostSerie(Base_URL+"getFavSerie/"+ user.id)


        serieFavori.setOnClickListener {
            post.PostVoid(Base_URL+"addFavSerie/" + user.id + "/" + serie.id)
            Toast.makeText(applicationContext, "Ajout réussi", Toast.LENGTH_SHORT).show()
            serieFavori.isFavorite = true
        }

        //enable back button on the toolbar
        serie_detail_toolbar.title = serie.title
        setSupportActionBar(serie_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        serie_view_pager.adapter = mSectionsPagerAdapter
        serie_view_pager.currentItem = 0

         serie_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(serie_tabs))
         serie_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(serie_view_pager))


        //play serie trailer
        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.serie_trailer)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = serie.video
                videoId?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })


        //print serie details
        serie_detail_title.text = serie.title
        Picasso.get().load(serie.picture).into(serie_detail_picture)

        for (i in 0..serie.genreList?.size!! - 1) {
            serie_detail_genre.text = serie.genreList?.get(i)?.genreType.toString() + ", "
        }

        serie_detail_released_date.text = serie.dateSortie
        serie_episodes_nbr.text = serie.nbrEpisodes.toString()
        serie_saisons_nbr.text = serie.nbrSaison.toString()
        serie_detail_resume.text = serie.resume
        serie_rating_bar.rating = ((serie.note+ "F").toFloat())/2
        serie_detail_rate.text = (((serie.note + "F").toFloat())/2).toString().take(3)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position){
                0 -> {
                   var saisons = ArrayList<Saison>()
                    var numbers = ArrayList<String>()
                    for(i in 1..serie.nbrSaison){
                        try {
                            saisons.add(convert.toSaison(post.PostObject(Base_URL+"getSerieSais/" + serie.id.toString() + "/" + i)))
                            numbers.add(i.toString())
                        }catch(e: Exception){
                            System.out.println(e)
                        }
                    }
                    SeasonFragment.newInstance(saisons,numbers)
                    //SeasonFragment()
                }
                1 -> {
                    AssociatedSeriesFragment.newInstance(post.PostSerie(Base_URL+"getSerieLi/"+serie.id.toString()))
                    //AssociatedSeriesFragment()
                }
                2 -> {
                    CommentsFragment.newInstance(post.PostComment(Base_URL+"getCSer/" + serie.id.toString()))
                    //CommentsFragment()
                }
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 3
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
