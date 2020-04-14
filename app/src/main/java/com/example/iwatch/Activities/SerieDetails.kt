package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.Serie
import com.example.iwatch.Fragments.AssociatedSeriesFragment
import com.example.iwatch.Fragments.CommentsFragment
import com.example.iwatch.Fragments.SeasonFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_serie_details.*

class SerieDetails : AppCompatActivity(), SeasonFragment.OnFragmentInteractionListener,
    AssociatedSeriesFragment.OnFragmentInteractionListener, CommentsFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var serie: Serie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)
        serie = intent.getSerializableExtra("serie") as Serie


        //enable back button on the toolbar
        serie_detail_toolbar.title = serie?.title
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


        //print serie details
        serie_detail_title.text= serie?.title
        serie_detail_released_date.text = serie?.dateSortie
        serie_episodes_nbr.text = serie?.nbrEpisodes!!.toString()
        serie_saisons_nbr.text = serie?.saisonList!!.size.toString()
        serie_detail_resume.text = serie?.resume

        serie_detail_genre.text = "hey"
        serie_rating_bar.rating = (serie?.note+ "F").toFloat()

        if(serie!!.picture != null){
            val url = serie!!.picture
            Picasso.get().load(url).into(serie_detail_picture)

        }

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
                0 -> SeasonFragment()
                1 -> AssociatedSeriesFragment()
                2 -> CommentsFragment()
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
