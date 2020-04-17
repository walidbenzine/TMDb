package com.example.iwatch.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Activities.SerieDetails
import com.example.iwatch.Adapters.OnSerieClickListener
import com.example.iwatch.Adapters.SerieAdapter
import com.example.iwatch.Entities.Serie

import com.example.iwatch.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private var series = ArrayList<Serie>()


/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteRoomFragment : Fragment(), OnSerieClickListener {


    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        series =  arguments?.getSerializable(ARG_PARAM1) as ArrayList<Serie>
        System.out.println("FAVORITE SERIES ===== "+ series)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favorite_room, container, false)

        val serieFavRecyclerView = v.findViewById<RecyclerView>(R.id.favorite_room_recycler_view)

        serieFavRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = SerieAdapter(series , this@FavoriteRoomFragment)
        }

        return v

    }

    override fun onSerieClicked(serie: Serie) {
        val serieDetailsIntent = Intent(this.context, SerieDetails::class.java)
        serieDetailsIntent.putExtra("serie", serie)
        startActivity(serieDetailsIntent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteRoomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Serie>) =
            FavoriteRoomFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                 }
            }
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
}
