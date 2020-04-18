package com.example.iwatch.Activities

import android.app.SearchManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Entities.*
import com.example.iwatch.Fragments.*
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

var convert = Convert()
var post = PostClass()
var user = User()

class Home : AppCompatActivity(),
    HomeFragment.OnFragmentInteractionListener, CinemaFragment.OnFragmentInteractionListener,
    SeriesFragment.OnFragmentInteractionListener, PersonsFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        user = intent.getSerializableExtra("user") as User


        // set the toolbar
        setSupportActionBar(toolbar)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        home_container.adapter = mSectionsPagerAdapter
        home_container.currentItem = 0

        home_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(home_container))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                Toast.makeText(this@Home, "looking for $query", Toast.LENGTH_LONG).show()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    class SectionsPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {

                0 -> {
                    System.out.println("CASE 0")
                    /*var frag = HomeFragment()
                    System.out.println("CASE 0")
                    doAsync {
                        var res = post.PostSerie(Base_URL+"getSerieLast")
                        var res2 = post.PostFilm("Base_URL+"getlast")
                        uiThread {
                                frag.serie = res
                                frag.films = res2
                        }
                    }
                    return frag*/
                    HomeFragment.newInstance(post.PostSerie(Base_URL+"getSerieLast"),post.PostFilm(Base_URL+"getlast"))

                }
                1 -> {
                    System.out.println("CASE 1")
					/*var frag = CinemaFragment()
                    doAsync {
                        var res = post.PostFilm(Base_URL+"getTopRated")
                        var res2 = post.PostCinema(Base_URL+"getAllRooms")
                        uiThread {
                            frag.films = res
                            frag.cinemas = res2
                        }
                    }
                    return frag*/
                    CinemaFragment.newInstance(post.PostFilm(Base_URL+"getTopRated"),post.PostCinema(Base_URL+"getAllRooms"))
                }
                2 -> {
                    System.out.println("CASE 2")
                    SeriesFragment.newInstance(post.PostSerie(Base_URL+"getSeriePopular"))
                }
                3 -> {
                    System.out.println("CASE 3")
                    PersonsFragment.newInstance(post.PostActor(Base_URL+"getActorPopular"))
                }
                4 -> {
                    System.out.println("CASE 4")
                    ProfileFragment.newInstance(user)
                }
                else -> {
                    System.out.println("CASE ELSE")
                    Fragment()
                }
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 5
        }

    }
}











