package org.systers.mentorship.view.fragments

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_change_password.view.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.utils.checkPasswordSecurity
import org.systers.mentorship.viewmodels.ChangePasswordViewModel

/**
 * The fragment is responsible for changing User password
 */
class ChangePasswordFragment : DialogFragment() {

    companion object {
        /**
         * Creates an instance of ChangePasswordFragment
         */
        fun newInstance() = ChangePasswordFragment()
    }

    private val changePasswordViewModel by lazy {
        ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)
    }
    private lateinit var changePasswordView: View
    private lateinit var currentPassword: String
    private lateinit var newPassword: String
    private lateinit var confirmPassword: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        changePasswordViewModel.successfulUpdate.observe(this, Observer { successful ->

            if (successful != null) {
                when {
                    successful -> Toast.makeText(activity, getString(R.string.password_updated), Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(activity, changePasswordViewModel.message, Toast.LENGTH_SHORT).show()
                }
            }
            dismiss()

        })

        changePasswordView = LayoutInflater.from(context).inflate(R.layout.fragment_change_password, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.change_password))
        builder.setView(changePasswordView)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val passwordDialog = dialog as? AlertDialog
        passwordDialog?.setCanceledOnTouchOutside(false)
        passwordDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            currentPassword = changePasswordView.tilCurrentPassword?.editText?.text.toString()
            newPassword = changePasswordView.tilNewPassword?.editText?.text.toString()
            confirmPassword = changePasswordView.tilConfirmPassword?.editText?.text.toString()

            changePasswordView.tilConfirmPassword?.error = null
            changePasswordView.tilNewPassword?.error = null

            if (validatePassword()) {
                changePasswordViewModel.changeUserPassword(ChangePassword(currentPassword, newPassword))
            }
        }
    }

    private fun validatePassword(): Boolean {
        return if (!newPassword.checkPasswordSecurity()) {
            changePasswordView.tilNewPassword?.error = getString(R.string.error_password_too_weak)
            false
        } else if (newPassword == confirmPassword && newPassword != currentPassword) {
            true
        } else {
            if (currentPassword == newPassword) {
                changePasswordView.tilNewPassword?.error = getString(R.string.current_new_password_should_not_same)
            }
            if (newPassword != confirmPassword) {
                changePasswordView.tilConfirmPassword?.error = getString(R.string.password_not_match)
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        changePasswordViewModel.successfulUpdate.removeObservers(this)
        changePasswordViewModel.successfulUpdate.value = null
    }
}
