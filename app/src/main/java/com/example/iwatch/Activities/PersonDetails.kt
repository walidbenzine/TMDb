package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.Actor
import com.example.iwatch.Entities.Movie
import com.example.iwatch.Fragments.FilmographyFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_person_details.*
private var person: Actor? = null
private var filmographie = ArrayList<Movie>()
private var conv = Convert()

class PersonDetails : AppCompatActivity(), FilmographyFragment.OnFragmentInteractionListener {

    private var mSectionsPagerAdapter:SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        //get person
        person = intent.getSerializableExtra("actor") as Actor

        //enable back button on the toolbar
        person_detail_toolbar.title = person?.firstName
        setSupportActionBar(person_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        person_view_pager.adapter = mSectionsPagerAdapter
        person_view_pager.currentItem = 0

        person_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(person_tabs))
        person_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(person_view_pager))

        //print persons details
        actor_detail_name.text = person?.lastName
        Picasso.get().load(person?.picture).into(person_detail_picture)
        actor_detail_birth_day.text = person?.dateOfBirth
        actor_detail_birth_place.text = person?.cityOfBirth
        actore_detail_biography.text = person?.bibliography
        actor_detail_popularity.text = person?.popularity

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
                0 -> FilmographyFragment()
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 1
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
