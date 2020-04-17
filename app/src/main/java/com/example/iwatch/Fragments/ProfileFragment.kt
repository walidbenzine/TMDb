package com.example.iwatch.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Activities.EditProfile
import com.example.iwatch.Activities.Favorite

import com.example.iwatch.Activities.UserGenres
import com.example.iwatch.Activities.post
import com.example.iwatch.Entities.User

import com.example.iwatch.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var user = User()
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user =  arguments?.getSerializable(ARG_PARAM1) as User
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        System.out.println("USERNAME = "+ user.firstName+" "+ user.firstName)


        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        //get user attributes
        var userName = v.findViewById<RecyclerView>(R.id.user_name) as TextView
        userName.text = user.firstName?.capitalize() + " " + user.firstName?.toUpperCase()

        var userPhone = v.findViewById<RecyclerView>(R.id.user_phone) as TextView
        userPhone.text = user.mobile

        var userJeton = v.findViewById<RecyclerView>(R.id.user_jeton) as TextView
        userJeton.text = user.jeton.toString()

        //open edit profile activity
        val btnEditProfile = v.findViewById<View>(R.id.btn_edit_profile) as Button
        btnEditProfile.setOnClickListener {
            val editProfile = Intent(this.context, EditProfile::class.java)
            editProfile.putExtra("user", user)
            startActivity(editProfile)
        }

        //open favorite activity
        val btnFavorite = v.findViewById<View>(R.id.btn_favorite) as LinearLayout
        btnFavorite.setOnClickListener {
            val favoriteIntent = Intent(this.context, Favorite::class.java)
            user.FavoriteMovies = post.PostFilm("http://scirusiwatch.herokuapp.com/getFavFilm/"+ user.id)
            user.FavoriteSeries = post.PostSerie("http://scirusiwatch.herokuapp.com/getFavSerie/"+ user.id)
            favoriteIntent.putExtra("user", user)
            startActivity(favoriteIntent)
        }

        //open user genre activity
        val btnGenres = v.findViewById<View>(R.id.btn_genre) as LinearLayout
        btnGenres.setOnClickListener {
            val genreIntent = Intent(this.context, UserGenres::class.java)
            //do the post here to get user genres
            startActivity(genreIntent)
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: User) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)

                }
            }
    }
}
