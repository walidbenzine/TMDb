package com.example.iwatch.Activities


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnSearchItemClickListener
import com.example.iwatch.Adapters.SearchAdapter
import com.example.iwatch.Entities.*
import com.example.iwatch.Enumerations.CommonItemSearchType
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
    ProfileFragment.OnFragmentInteractionListener, OnSearchItemClickListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mSearchAdapter: SearchAdapter? = null
    private var movieList = ArrayList<Movie>()
    private var serieList = ArrayList<Serie>()
    private var cinemaList = ArrayList<Cinema>()
    private var actorList = ArrayList<Actor>()
    private var commonItemSearch = ArrayList<CommonItemSearch>()

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

        /*val movieService = ApiFactory.scirusAPI
        GlobalScope.launch(Dispatchers.Default) {
            val popularMovieRequest = movieService.getPopularMovie()
            try {
                val response = popularMovieRequest.await()
                val movieResponse = response //This is single object Tmdb Movie response
                popularMovies = movieResponse?.result // This is list of TMDB Movie
                System.out.println("size: " + popularMovies.toString())
            }catch (e: Exception){
                System.out.println("error: " + e)
            }
        }*/

        getCommonItemSearch()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val searchRecyclerView = findViewById<RecyclerView>(R.id.search_recycler_view) as RecyclerView
        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
        }

        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                return true
            }
        })

        val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Search"

        MenuItemCompat.setOnActionExpandListener(
            searchItem,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    searchRecyclerView.visibility = View.VISIBLE
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    searchRecyclerView.visibility = View.GONE
                    return true
                }
            })

        searchRecyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.VERTICAL
            )
        )
        searchRecyclerView.setHasFixedSize(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSearchAdapter?.filter?.filter(newText)
                return false
            }

        })

        mSearchAdapter = SearchAdapter(commonItemSearch, this)
        searchRecyclerView.apply {
            adapter = mSearchAdapter
        }

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

    override fun onSearchItemClicked(commonItem: CommonItemSearch) {
        when(commonItem.type){
            CommonItemSearchType.Movie -> getMovie(movieList, commonItem)
            CommonItemSearchType.Serie -> getSerie(serieList, commonItem)
            CommonItemSearchType.Cinema -> getCinema(cinemaList, commonItem)
            CommonItemSearchType.Actor -> getActor(actorList, commonItem)
        }
    }

    fun getCommonItemSearch(){
        movieList = post.PostFilm("http://scirusiwatch.herokuapp.com/getlast")
        movieList.addAll(post.PostFilm("http://scirusiwatch.herokuapp.com/getTopRated"))

        serieList = post.PostSerie("http://scirusiwatch.herokuapp.com/getSerieLast")
        serieList.addAll(post.PostSerie("http://scirusiwatch.herokuapp.com/getSeriePopular"))

        cinemaList = post.PostCinema("http://scirusiwatch.herokuapp.com/getAllRooms")

        actorList = post.PostActor("http://scirusiwatch.herokuapp.com/getActorPopular")

        for(movie in movieList){
            var commonItem = CommonItemSearch()
            commonItem.id = movie.id
            commonItem.name = movie.title
            commonItem.type = CommonItemSearchType.Movie
            commonItemSearch.add(commonItem)
        }

        for(serie in serieList){
            var commonItem = CommonItemSearch()
            commonItem.id = serie.id
            commonItem.name = serie.title
            commonItem.type = CommonItemSearchType.Serie
            commonItemSearch.add(commonItem)
        }

        for(cinema in cinemaList){
            var commonItem = CommonItemSearch()
            commonItem.id = cinema.id!!
            commonItem.name = cinema.nom
            commonItem.type = CommonItemSearchType.Cinema
            commonItemSearch.add(commonItem)
        }

        for(actor in actorList){
            var commonItem = CommonItemSearch()
            commonItem.id = actor.id
            commonItem.name = actor.lastName
            commonItem.type = CommonItemSearchType.Actor
            commonItemSearch.add(commonItem)
        }
    }

    fun getMovie(movieList: ArrayList<Movie>, commonItem: CommonItemSearch){
        var movie = Movie()
        for(mv in movieList){
            if(mv.id == commonItem.id){
                movie = mv
            }
        }
        val movieDetailsIntent = Intent(this, MovieDetails::class.java)
        movieDetailsIntent.putExtra("movie", movie)
        startActivity(movieDetailsIntent)
    }

    fun getSerie(serieList: ArrayList<Serie>, commonItem: CommonItemSearch){
        var serie = Serie()
        for(sr in serieList){
            if(sr.id == commonItem.id){
                serie = sr
            }
        }
        val serieDetailsIntent = Intent(this, SerieDetails::class.java)
        serieDetailsIntent.putExtra("serie", serie)
        startActivity(serieDetailsIntent)
    }

    fun getCinema(cinemaList: ArrayList<Cinema>, commonItem: CommonItemSearch){
        var cinema = Cinema()
        for(cn in cinemaList){
            if(cn.id == commonItem.id){
                cinema = cn
            }
        }
        System.out.println("cinema found: " + cinema.nom)
    }

    fun getActor(actorList: ArrayList<Actor>, commonItem: CommonItemSearch){
        var actor = Actor()
        for(ac in actorList){
            if(ac.id == commonItem.id){
                actor = ac
            }
        }
        val serieDetailsIntent = Intent(this, PersonDetails::class.java)
        serieDetailsIntent.putExtra("actor", actor)
        startActivity(serieDetailsIntent)
    }

}











