package org.systers.mentorship.view.fragments

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_reset_password.view.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.ResetPassword
import org.systers.mentorship.viewmodels.ResetPasswordViewModel

/**
 * The fragment is responsible for changing User password
 */
class ResetPasswordFragment : DialogFragment() {

    companion object {
        /**
         * Creates an instance of ResetPasswordFragment
         */
        fun newInstance() = ResetPasswordFragment()
    }

    private lateinit var resetPasswordViewModel: ResetPasswordViewModel
    private lateinit var resetDialogView: View
    private lateinit var currentPassword: String
    private lateinit var newPassword: String
    private lateinit var confirmPassword: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        resetPasswordViewModel = ViewModelProviders.of(this).get(ResetPasswordViewModel::class.java)
        resetPasswordViewModel.successfulUpdate.observe(this, Observer { successful ->

            if (successful != null)
                Toast.makeText(activity, resetPasswordViewModel.message, Toast.LENGTH_SHORT).show()
            dismiss()

        })

        resetDialogView = LayoutInflater.from(context).inflate(R.layout.fragment_reset_password, null)
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(getString(R.string.reset_password))
        builder.setView(resetDialogView)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        return builder.create()
    }

    override fun onResume() {
        super.onResume()
        val passwordDialog = dialog as AlertDialog

        passwordDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            currentPassword = resetDialogView.tilCurrentPassword?.editText?.text.toString()
            newPassword = resetDialogView.tilNewPassword?.editText?.text.toString()
            confirmPassword = resetDialogView.tilConfirmPassword?.editText?.text.toString()
            if (newPassword != confirmPassword) {
                resetDialogView.tilConfirmPassword?.error = getString(R.string.password_not_match)
            } else {
                resetPasswordViewModel.changePassword(ResetPassword(currentPassword, newPassword))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        resetPasswordViewModel.successfulUpdate.removeObservers(this)
        resetPasswordViewModel.successfulUpdate.value = null
    }
}