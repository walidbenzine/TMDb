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
import fr.upem.myapplication.Film
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

var convert = Convert()
var user = User()

class Home : AppCompatActivity(),
    HomeFragment.OnFragmentInteractionListener, CinemaFragment.OnFragmentInteractionListener,
    SeriesFragment.OnFragmentInteractionListener, PersonsFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_home)

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
    class SectionsPagerAdapter(fm: FragmentManager)  : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position){
                0 -> HomeFragment.newInstance(PostSerie("http://10.0.2.2:8080/getSerieLast"),PostFilm("http://10.0.2.2:8080/getlast"))
                1 -> CinemaFragment.newInstance(PostFilm("http://10.0.2.2:8080/getTopRated"), ArrayList<Cinema>() )
                2 -> SeriesFragment.newInstance(PostSerie("http://10.0.2.2:8080/getSeriePopular"))
                3 -> PersonsFragment.newInstance(PostActor("http://10.0.2.2:8080/getActorPopular"))
                4 -> ProfileFragment.newInstance(user)
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 5
        }

    }
}

fun PostSerie(url: String) : ArrayList<Serie> {
    var arrayseries = ArrayList<Serie>()
    val x = try {
        URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
    catch(e: Exception){
        System.out.println(e)
    }
    if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
        var jsonarray = JSONArray(x.toString())
        for( i in 0 until jsonarray.length()){
            arrayseries.add(convert.toSerie(JSONObject(jsonarray.get(i).toString())))
        }
        return arrayseries
    }
    return arrayseries
}


fun PostFilm(url: String) : ArrayList<Film> {
    var arrayfilms = ArrayList<Film>()
    val x = try {
        URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
    catch(e: Exception){
        System.out.println(e)
    }
    if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
        var jsonarray = JSONArray(x.toString())
        for( i in 0 until jsonarray.length()){
            arrayfilms.add(convert.toFilm(JSONObject(jsonarray.get(i).toString())))
        }
        return arrayfilms
    }
    return arrayfilms
}

fun PostActor(url: String) : ArrayList<Actor> {
    var arrayactors = ArrayList<Actor>()
    val x = try {
        URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
    catch(e: Exception){
        System.out.println(e)
    }
    if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
        var jsonarray = JSONArray(x.toString())
        for( i in 0 until jsonarray.length()){
            arrayactors.add(convert.toActor(JSONObject(jsonarray.get(i).toString())))
        }
        return arrayactors
    }
    return arrayactors
}


fun PostSaison(url: String) : ArrayList<Saison> {
    var arraySaison = ArrayList<Saison>()
    val x = try {
        URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() } }
    catch(e: Exception){
        System.out.println(e)
    }
    if(!x.toString().isNullOrEmpty() && x.toString() != "null"){
        var jsonarray = JSONArray(x.toString())
        for( i in 0 until jsonarray.length()){
            arraySaison.add(convert.toSaison(JSONObject(jsonarray.get(i).toString())))
        }
        return arraySaison
    }
    return arraySaison
}
