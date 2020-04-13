package com.example.iwatch.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.iwatch.Fragments.AssociatedSeriesFragment
import com.example.iwatch.Fragments.CommentsFragment
import com.example.iwatch.Fragments.SeasonFragment
import com.example.iwatch.R
import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.activity_serie_details.*

class SerieDetails : AppCompatActivity(), SeasonFragment.OnFragmentInteractionListener,
    AssociatedSeriesFragment.OnFragmentInteractionListener, CommentsFragment.OnFragmentInteractionListener{

    private   var titre_s : TextView?= null
    private   var date_s:TextView?= null
    private   var date_r:TextView?= null
    private   var genre_s:TextView?= null
    private   var nbr_epi:TextView?= null
    private   var nbr_saison:TextView?= null
    private   var description_s:TextView?= null


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        //serie_view_pager.adapter = mSectionsPagerAdapter
        //serie_view_pager.currentItem = 0

       // serie_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(serie_tabs))
        //serie_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(serie_view_pager))


        var titre = intent.getSerializableExtra("titre")
        var details = intent.getSerializableExtra("details")
        var datesortie = intent.getSerializableExtra("datesortie")
        var daterealisation = intent.getSerializableExtra("tidaterealisationtre")

        titre_s =findViewById( R.id.serie_detail_title)
        date_s =findViewById( R.id.serie_detail_released_date)
        //date_r =findViewById( R.id.serie_detail_released_date)
        genre_s = findViewById( R.id.serie_detail_genre)
        nbr_epi= findViewById(R.id.serie_episodes_nbr)
        nbr_saison = findViewById(R.id.serie_saisons_nbr)
        description_s =findViewById(R.id.serie_detail_resume)



        titre_s!!.text =titre!!.toString()
        date_s!!.text =datesortie!!.toString()
        //genre_s!!.text =
        description_s!!.text =details!!.toString()

        Toast.makeText(this, "titre "+titre, Toast.LENGTH_LONG).show()


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
