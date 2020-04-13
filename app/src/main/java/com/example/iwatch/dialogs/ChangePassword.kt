package com.example.iwatch.dialogs

import com.example.iwatch.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment


class ChangePassword() : AppCompatDialogFragment() {
    private var oldPassword: EditText? = null
    private var newPassword: EditText? = null
    private var confirmPassword: EditText? = null
    private var listener: ChangePasswordDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view: View = inflater.inflate(R.layout.change_password, null)
        builder.setView(view)
            .setTitle("Changer Mot De Passe")
            .setNegativeButton("cancel",
                DialogInterface.OnClickListener { dialogInterface, i -> })
            .setPositiveButton("Confirmer",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    val oldPwd = oldPassword!!.text.toString()
                    val newPwd = newPassword!!.text.toString()
                    val confirmPwd = confirmPassword!!.text.toString()
                    listener!!.applyTexts(oldPwd, newPwd, confirmPwd)
                })
        oldPassword = view.findViewById(R.id.old_pwd)
        newPassword = view.findViewById(R.id.new_pwd)
        confirmPassword = view.findViewById(R.id.confirm_pwd)
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as ChangePasswordDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString().toString() +
                        "must implement ChangePasswordDialogListener"
            )
        }
    }

    interface ChangePasswordDialogListener {
        fun applyTexts(oldPassword: String?, newPassword: String?, confirmPassword: String?)
    }

}