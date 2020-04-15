package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.User
import com.example.iwatch.Fragments.FavoriteMovieFragment
import com.example.iwatch.Fragments.FavoriteRoomFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_favorite.*

class Favorite : AppCompatActivity(), FavoriteMovieFragment.OnFragmentInteractionListener,
    FavoriteRoomFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        user = intent.getSerializableExtra("user") as User

        favorite_toolbar.title = "Favorite"
        setSupportActionBar(favorite_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        favorite_view_pager.adapter = mSectionsPagerAdapter
        favorite_view_pager.currentItem = 0

        favorite_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(favorite_tabs))
        favorite_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(favorite_view_pager))
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

    class SectionsPagerAdapter(fm: FragmentManager)  : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position){
                0 -> FavoriteMovieFragment.newInstance(user.FavoriteMovies)
                1 -> FavoriteRoomFragment.newInstance(user.FavoriteSeries)
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
