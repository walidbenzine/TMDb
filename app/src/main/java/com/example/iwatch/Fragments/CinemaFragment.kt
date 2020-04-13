package com.example.iwatch.Fragments

import android.content.Context
import android.content.Intent
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
import com.example.iwatch.Activities.MovieDetails
import com.example.iwatch.Adapters.CinemaRoomAdapter
import com.example.iwatch.Adapters.MovieAdapter
import com.example.iwatch.Adapters.OnMovieClickListener
import com.example.iwatch.Entities.Cinema
import com.example.iwatch.Entities.Movie
import com.example.iwatch.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CinemaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CinemaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CinemaFragment : Fragment(), OnMovieClickListener {
    // TODO: Rename and change types of parameters
    private var films =  ArrayList<Movie>()
    private var cinemas =  ArrayList<Cinema>()

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        films = arguments?.getSerializable(ARG_PARAM1) as ArrayList<Movie>

        @Suppress("UNCHECKED_CAST")
        cinemas = arguments?.getSerializable(ARG_PARAM2) as ArrayList<Cinema>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        System.out.println("FIRST FILM TITLE = "+ films.get(0).title)
        System.out.println("FIRST CINE NAME = "+ cinemas.get(0).nom)

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_cinema, container, false)
        val cinemaRecyclerView = v.findViewById<RecyclerView>(R.id.cinema_recycler_view)

        val btnCinemaMovies = v.findViewById<View>(R.id.btn_movies_cinema) as Button
        btnCinemaMovies.setBackgroundResource(R.drawable.clicked_button)
        btnCinemaMovies.setTextColor(Color.parseColor("#ffffff"))

        val btnRooms = v.findViewById<View>(R.id.btn_rooms) as Button
        btnRooms.setBackgroundResource(R.drawable.no_clicked_button)
        btnRooms.setTextColor(Color.parseColor("#EF4B53"))

        cinemaRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = MovieAdapter(films, this@CinemaFragment)
        }


        btnCinemaMovies.setOnClickListener {
            cinemaRecyclerView.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = MovieAdapter(films, this@CinemaFragment)
            }

            btnCinemaMovies.setBackgroundResource(R.drawable.clicked_button)
            btnCinemaMovies.setTextColor(Color.parseColor("#ffffff"))

            btnRooms.setBackgroundResource(R.drawable.no_clicked_button)
            btnRooms.setTextColor(Color.parseColor("#EF4B53"))
        }

        btnRooms.setOnClickListener {
            cinemaRecyclerView.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = CinemaRoomAdapter(cinemas)
            }
            btnRooms.setBackgroundResource(R.drawable.clicked_button)
            btnRooms.setTextColor(Color.parseColor("#ffffff"))

            btnCinemaMovies.setBackgroundResource(R.drawable.no_clicked_button)
            btnCinemaMovies.setTextColor(Color.parseColor("#EF4B53"))
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
         * @return A new instance of fragment CinemaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Movie>, param2: ArrayList<Cinema>) =
            CinemaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMovieClicked(movie: Movie) {
        val movieDetailsIntent = Intent(this.context, MovieDetails::class.java)
        movieDetailsIntent.putExtra("movie", movie)
        startActivity(movieDetailsIntent)
    }
}
