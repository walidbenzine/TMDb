package com.example.iwatch.Fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.MovieAdapter
import com.example.iwatch.Adapters.SerieAdapter
import com.example.iwatch.Entities.Movie
import com.example.iwatch.Entities.Serie

import com.example.iwatch.R

import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var serie =  ArrayList<Serie>()
    var films =  ArrayList<Movie>()

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        serie = arguments?.getSerializable(ARG_PARAM1) as ArrayList<Serie>
        @Suppress("UNCHECKED_CAST")
        films = arguments?.getSerializable(ARG_PARAM2) as ArrayList<Movie>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        System.out.println("FIRST SERIE TITLE = "+ serie.get(0).title)
        //System.out.println("FIRST FILM TITLE =  "+ films.get(0).title)

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val btnMovies = v.findViewById<View>(R.id.btn_movies) as Button
        btnMovies.setBackgroundResource(R.drawable.clicked_button)
        btnMovies.setTextColor(Color.parseColor("#ffffff"))

        val btnSeries = v.findViewById<View>(R.id.btn_series) as Button
        btnSeries.setBackgroundResource(R.drawable.no_clicked_button)
        btnSeries.setTextColor(Color.parseColor("#EF4B53"))

        val serieRecyclerView = v.findViewById<RecyclerView>(R.id.home_recycler_view)



        serieRecyclerView.apply {
            serieRecyclerView!!.layoutManager = LinearLayoutManager(this.context)
            layoutManager = LinearLayoutManager(this.context)
            adapter = MovieAdapter(films)
        }

        btnMovies.setOnClickListener {
            serieRecyclerView.apply {
                serieRecyclerView!!.layoutManager = LinearLayoutManager(this.context)
                layoutManager = LinearLayoutManager(this.context)
                adapter = MovieAdapter(films)
                btnSeries.setBackgroundResource(R.drawable.no_clicked_button)
                btnMovies.setBackgroundResource(R.drawable.clicked_button)
                btnMovies.setTextColor(Color.parseColor("#ffffff"))
                btnSeries.setTextColor(Color.parseColor("#EF4B53"))
            }

        }
        btnSeries.setOnClickListener {
            serieRecyclerView.apply {
                serieRecyclerView!!.layoutManager = LinearLayoutManager(this.context)
                layoutManager = LinearLayoutManager(this.context)
                adapter = SerieAdapter(serie)
                btnMovies.setBackgroundResource(R.drawable.no_clicked_button)
                btnSeries.setBackgroundResource(R.drawable.clicked_button)
                btnSeries.setTextColor(Color.parseColor("#ffffff"))
                btnMovies.setTextColor(Color.parseColor("#EF4B53"))
            }

        }
        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Serie>, param2: ArrayList<Movie>) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }
}