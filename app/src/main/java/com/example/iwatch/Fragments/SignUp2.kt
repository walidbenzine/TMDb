package com.example.iwatch.Fragments

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.iwatch.Activities.ConfirmRegistration
import com.example.iwatch.Dialogs.ChooseGenre
import com.example.iwatch.Entities.Genre
import com.example.iwatch.Entities.User
import com.example.iwatch.Enumerations.GenreType
import com.example.iwatch.R
import kotlinx.android.synthetic.main.fragment_sign_up2.*
import java.util.*
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val PERMISSION_CODE = 1000
private const val IMAGE_CAPTURE_CODE = 1001

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUp2.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUp2.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var code = ""
    var usr = User()
    private var listener: OnFragmentInteractionListener? = null
    var imageUri:  Uri? = null

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

        var genreSpinner = v.findViewById<View>(R.id.genre_spinner) as LinearLayout
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


        var btnTakePicture = v.findViewById<View>(R.id.btn_take_pic) as Button
        btnTakePicture.setOnClickListener {
            takePicture()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){

            var bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, imageUri)
            picture.rotation = 90F
            picture.setImageBitmap(bitmap)
            usr.picture = getRealPathFromURI(imageUri!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
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

    fun openDialog() {
        val genreDialog = ChooseGenre()
        genreDialog.listener = object : ChooseGenre.ChooseGenreDialogListener {
            override fun applyTexts(selectedGenre: ArrayList<String>) {
                usr.genrePref.clear()
                for (genre in selectedGenre) {
                    when (genre) {
                        "Adventure" -> usr.genrePref?.add(Genre(GenreType.Adventure))
                        "Action" -> usr.genrePref?.add(Genre(GenreType.Action))
                        "Thriller" -> usr.genrePref?.add(Genre(GenreType.Thriller))
                        "Drama" -> usr.genrePref?.add(Genre(GenreType.Drama))
                        "Romance" -> usr.genrePref?.add(Genre(GenreType.Romance))
                        "War" -> usr.genrePref?.add(Genre(GenreType.War))
                        "Comedy" -> usr.genrePref?.add(Genre(GenreType.Comedy))
                    }
                }
            }
        }
        fragmentManager?.let { genreDialog.show(it, "genre dialog") }
    }

    fun takePicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context?.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                || context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            ) {
                //permission was not enabled
               val permission = arrayOf(
                   Manifest.permission.CAMERA,
                   Manifest.permission.WRITE_EXTERNAL_STORAGE
               )
                //show pop up to request permission
                requestPermissions(permission, PERMISSION_CODE)
            } else {
                //permission already granted
                openCamera()
            }
        } else {
            //system os is < marshmallow
            openCamera()
        }
    }

    fun openCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "profile_picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From The Camera")
        imageUri = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission from pop up was granted
                    openCamera()
                }else{
                    //permission from pop up was denied
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun getRealPathFromURI(uri: Uri) : String{
        var path = ""
        if (context?.contentResolver != null) {
            var cursor = context?.contentResolver?.query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
    return path
}


}
