package com.example.iwatch.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.iwatch.Enumerations.GenreType
import com.example.iwatch.R

class ChooseGenre : AppCompatDialogFragment() {
    private var selectedGenres = ArrayList<String>()
    private var listener: ChooseGenreDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setTitle("Choisir Genre")
            .setMultiChoiceItems(
                R.array.genre_array,
                null,
                DialogInterface.OnMultiChoiceClickListener { dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    var items = activity?.resources?.getStringArray(R.array.genre_array)
                    if(b){
                        items?.get(i)?.let { selectedGenres.add(it) }
                    }else{
                        if(selectedGenres.contains(items?.get(i))){
                            selectedGenres.remove(items?.get(i))
                        }
                    }
                })
            .setNegativeButton("cancel",
                DialogInterface.OnClickListener { dialogInterface, i -> })
            .setPositiveButton("Choisir",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    var choosenGenres = ArrayList<String>()
                    for(genre in selectedGenres){
                        choosenGenres.add(genre)
                    }
                    listener!!.applyTexts(choosenGenres)
                })
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as ChooseGenreDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString().toString() +
                        "must implement ChooseGenreDialogListener"
            )
        }
    }

    interface ChooseGenreDialogListener {
        fun applyTexts(selectedGenre: ArrayList<String>)
    }
}