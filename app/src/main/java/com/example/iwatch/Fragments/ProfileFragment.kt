package com.example.iwatch.Fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Activities.*

import com.example.iwatch.Entities.User

import com.example.iwatch.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val PREF_NAME = "Scirus-Y"
private var PRIVATE_MODE = 0

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
    var userPic = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(ARG_PARAM1) as User
            userPic = it.getString("tof") as String
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        System.out.println("USERNAME = "+ user.firstName+" "+ user.firstName)


        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        //get user attributes
        var userPicture = v.findViewById<ImageView>(R.id.user_picture) as ImageView
        userPicture.setImageBitmap(decodeImage(encodeImage(userPic)))

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
            doAsync {
                user.FavoriteMovies = post.PostFilm(Base_URL+"getFavFilm/"+ user.id)
                user.FavoriteSeries = post.PostSerie(Base_URL+"getFavSerie/"+ user.id)
                uiThread {
                    favoriteIntent.putExtra("user", user)
                    favoriteIntent.putExtra("tof", userPic)
                    startActivity(favoriteIntent)
                }
            }
       }

    /*    val btndec = v.findViewById<View>(R.id.btn_disconnect) as LinearLayout
        btndec.setOnClickListener {
<<<<<<< HEAD
           // Toast.makeText("See you later :)", Toast.LENGTH_LONG).show()

            val favoriteIntent = Intent(this.context, MainActivity::class.java)
=======
>>>>>>> 06ad45f516fe695008f781f3b34aaf05f47f0c5f

        }*/

        //open user genre activity
        val btnGenres = v.findViewById<View>(R.id.btn_genre) as LinearLayout
        btnGenres.setOnClickListener {
            doAsync {
                user.genrePref = convert.togenrePref(post.PostArray(Base_URL+"getuserGenre/"+user.id.toString()))
                uiThread {
                    val genreIntent = Intent(getActivity(), UserGenres::class.java)
                    genreIntent.putExtra("user", user)
                    startActivity(genreIntent)
                }
            }
        }

        val btnDisconnect = v.findViewById<View>(R.id.btn_disconnect) as LinearLayout
        btnDisconnect.setOnClickListener {

            val pref = activity!!.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            val homeIntent = Intent(this.context, MainActivity::class.java)

            pref.edit().clear().commit()
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(homeIntent)
            activity!!.finish()
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
        fun newInstance(param1: User, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString("tof", param2)
                }
            }
    }

    fun decodeImage(encodedImage: String): Bitmap {
        var imageBytes = Base64.getDecoder().decode(encodedImage)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }

    fun encodeImage(imagePath: String): String{

        val bytes = File(imagePath).readBytes()
        val base64 = Base64.getEncoder().encodeToString(bytes)

        return base64
    }
}
