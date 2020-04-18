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
import com.example.iwatch.Activities.SeasonDetails
import com.example.iwatch.Adapters.SeasonAdapter
import com.example.iwatch.Entities.Saison

import com.example.iwatch.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SeasonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SeasonFragment : Fragment(), SeasonAdapter.OnSeasonClickListener {




    // TODO: Rename and change types of parameters
    private var saisons = ArrayList<Saison>()
    private var numbers = ArrayList<String>()

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        saisons = arguments?.getSerializable(ARG_PARAM1) as ArrayList<Saison>
        numbers = arguments?.getSerializable(ARG_PARAM2) as ArrayList<String>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_season, container, false)
        val saisonRecyclerView = v.findViewById<RecyclerView>(R.id.season_view_pager)

        saisonRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = SeasonAdapter(saisons, this@SeasonFragment)
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
         * @return A new instance of fragment SeasonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Saison>, param2 :ArrayList<String>) =
            SeasonFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }
    override fun onSeasonClicked(season: Saison) {
        val seasonDetailsIntent = Intent(this.context, SeasonDetails::class.java)
        System.out.println("EPISODES ====== " +season.episodeList)
        var position = saisons.indexOf(season)
        seasonDetailsIntent.putExtra("season", season)
        seasonDetailsIntent.putExtra("number", numbers.get(position))
        startActivity(seasonDetailsIntent)
    }
}
