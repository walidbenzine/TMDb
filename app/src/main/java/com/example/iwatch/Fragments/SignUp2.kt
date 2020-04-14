package com.example.iwatch.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.example.iwatch.Activities.ConfirmRegistration
import kotlinx.android.synthetic.main.fragment_sign_up2.*
import android.widget.Button
import com.example.iwatch.Dialogs.ChangePassword
import com.example.iwatch.Dialogs.ChooseGenre
import com.example.iwatch.Entities.User
import com.example.iwatch.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUp2.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUp2.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp2 : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var code = ""
    var usr = User()
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_sign_up2, container, false)
        var genreSpinner =  v.findViewById<View>(R.id.genre_spinner) as LinearLayout
        genreSpinner.setOnClickListener {
            openDialog()
        }

        var bundle = this.arguments

        if (bundle != null) {
            usr = bundle.getSerializable("user") as User
        }

        var btnConfirmSignUp = v.findViewById<View>(R.id.btn_signup_confirm) as Button
        btnConfirmSignUp.setOnClickListener {

            usr.mobile = phone.text.toString()
            usr.adresse = add.text.toString()

            val smsManager = SmsManager.getDefault()
            code = String.format("%04d", Random.nextInt(10000))
            smsManager.sendTextMessage(
                usr.mobile,
                "Verification",
                "To verify your Iwatch account, please enter this code: " + code,
                null,
                null
            )
            System.out.println("SMS SENT to" + phone)

            val intent = Intent(activity, ConfirmRegistration::class.java)
            intent.putExtra("code", code)
            intent.putExtra("user", usr)
            startActivity(intent)
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
         * @return A new instance of fragment SignUp2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    fun openDialog() {
        val genreDialog = ChooseGenre()
        fragmentManager?.let { genreDialog.show(it, "genre dialog") }
    }

}
