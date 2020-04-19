package com.example.iwatch.Fragments

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Activities.user
import com.example.iwatch.Adapters.CommentAdapter
import com.example.iwatch.Entities.Comment
import com.example.iwatch.Entities.User
import com.example.iwatch.R
import kotlinx.android.synthetic.main.comment_item.*
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_sign_up2.view.*
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [CommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var listener: OnFragmentInteractionListener? = null
    lateinit var commentView: View
    lateinit var v: View

    var comments = ArrayList<Comment>()
    var from = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        comments = arguments?.getSerializable(ARG_PARAM1) as ArrayList<Comment>
        from = arguments?.getString(ARG_PARAM2) as String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        commentView = inflater.inflate(R.layout.comment_item, container, false)

        v = inflater.inflate(R.layout.fragment_comments, container, false)
        val commentRecyclerView = v.findViewById<RecyclerView>(R.id.comments_recycler_view)
        var userPicture = v.findViewById<ImageView>(R.id.add_comment_user_picture)
        var btnAddComment = v.findViewById<EditText>(R.id.add_comment)

        try {
            System.out.println("COMMENTS ==== " + comments)
        } catch (e: Exception) {
            System.out.println(e)
        }

        commentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = CommentAdapter(comments)
        }

        userPicture.setImageResource(R.mipmap.ic_drama)

        btnAddComment.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {

                addComment(add_comment.text.toString(), user)
                add_comment.text.clear()

                when (from) {
                    "movie" -> PostVoid(
                        "http://scirusiwatch.herokuapp.com/addcomm/${user.id}/${add_comment.text.toString()}/movie/${user.login}"
                    )
                    "serie" -> PostVoid(
                        "http://scirusiwatch.herokuapp.com/addcomm/${user.id}/${add_comment.text.toString()}/serie/${user.login}"
                    )
                }

                true
            }
            false
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

    fun PostVoid(url: String) {
        val x = try {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: java.lang.Exception) {
            System.out.println(e)
        }
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
         * @return A new instance of fragment CommentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Comment>, param2: String) =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun addComment(comment: String, user: User) {

        var newComment = commentView.findViewById<LinearLayout>(R.id.new_comment)

        newComment.comment_text.text = comment
        newComment.comment_user_name.text = user.firstName + " " + user.lastName
        //newComment.comment_user_picture.setImageBitmap(decodeImage(decodeValue(user.picture)))

        var cm = v.findViewById<LinearLayout>(R.id.comment_fragment)
        cm.removeViewAt(cm.childCount -1)
        cm.addView(newComment)
    }

    fun decodeImage(encodedImage: String): Bitmap {
        var imageBytes = Base64.getDecoder().decode(encodedImage)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }

    fun decodeValue(value: String): String {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (ex: UnsupportedEncodingException) {
            throw RuntimeException(ex.cause);
        }
    }
}
