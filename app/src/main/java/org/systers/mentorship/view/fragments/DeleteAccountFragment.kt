package org.systers.mentorship.view.fragments


import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_delete_account.view.*

import org.systers.mentorship.R

/*
* Author: Abhinav Srivastava
* Display Name: asawesome7
* email ID: abhinav.wii@gmail.com*/

/**
 * A simple [Fragment] subclass.
 */
class DeleteAccountFragment : DialogFragment() {

    companion object {
        /**
         * Creates an instance of DeleteAccountFragment
         */
        fun newInstance() = DeleteAccountFragment()
    }

    private lateinit var deleteAccountView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        deleteAccountView = LayoutInflater.from(context).inflate(R.layout.fragment_delete_account, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.settings_DeleteAccountTitle))
        builder.setMessage(getString(R.string.settings_DeleteAccountMessage))
        builder.setView(deleteAccountView)
        builder.setPositiveButton(getString(R.string.settings_DeleteAccountDelete)) { dialog, _ ->
            Toast.makeText(activity, "Account delete initiated", Toast.LENGTH_SHORT).show()
            //put backend code for deletion
        }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val deleteAccountDialog = dialog as? AlertDialog
        deleteAccountDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false

        deleteAccountView.settingsDeleteInp?.editText?.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {

                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        deleteAccountDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = s.toString() == "DELETE"
                    }

                })

    }



}
