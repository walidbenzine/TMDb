package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.Saison
import com.example.iwatch.Fragments.EpisodesFragment
import com.example.iwatch.Fragments.SeasonActorsFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_season_details.*

private var season = Saison()
private var number =""

class SeasonDetails : AppCompatActivity(), EpisodesFragment.OnFragmentInteractionListener,
    SeasonActorsFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)

        //get season
        season = intent.getSerializableExtra("season") as Saison
        number = intent.getSerializableExtra("number") as String


        //enable back button on the toolbar
        season_detail_toolbar.title = ""
        setSupportActionBar(season_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        season_view_pager.adapter = mSectionsPagerAdapter
        season_view_pager.currentItem = 0

        season_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(season_tabs))
        season_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(season_view_pager))
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
                    EpisodesFragment.newInstance(season.episodeList)
                }
                1 -> {
                    SeasonActorsFragment.newInstance(post.PostActor(Base_URL+"getSaisonActs/" + season.id.toString() + "/" + number))
                    //SeasonActorsFragment()
                }
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 2
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
