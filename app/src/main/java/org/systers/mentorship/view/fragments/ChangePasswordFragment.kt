package org.systers.mentorship.view.fragments

import android.app.Dialog
import android.content.IntentSender
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.common.api.ResolvableApiException
import kotlinx.android.synthetic.main.fragment_change_password.view.*
import org.systers.mentorship.MentorshipApplication.Companion.credentialsClient
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.SettingsActivity
import org.systers.mentorship.viewmodels.ChangePasswordViewModel

/**
 * The fragment is responsible for changing User password
 */
class ChangePasswordFragment : DialogFragment() {

    companion object {
        /**
         * Creates an instance of ChangePasswordFragment
         */
        fun newInstance(email: String?, username: String?) = ChangePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(EMAIL, email)
                putString(USERNAME, username)
            }
        }
    }

    private val EMAIL = "email"
    private val USERNAME = "username"

    private lateinit var changePasswordViewModel: ChangePasswordViewModel
    private lateinit var changePasswordView: View
    private lateinit var currentPassword: String
    private lateinit var newPassword: String
    private lateinit var confirmPassword: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        changePasswordViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)
        changePasswordViewModel.successfulUpdate.observe(this, Observer { successful ->

            if (successful != null) {
                when {
                    successful -> {
                        Toast.makeText(activity, getString(R.string.password_updated), Toast.LENGTH_SHORT).show()
                        // we need to delete both types of credentials, as we don't know
                        // which type of data user used at LoginActivity
                        val oldCredential = Credential.Builder(arguments?.getString(EMAIL))
                                .setName(arguments?.getString(USERNAME))
                                .setPassword(currentPassword)
                                .build()
                        val oldCredentialOnlyUsername = Credential.Builder(arguments?.getString(USERNAME))
                                .setName(arguments?.getString(USERNAME))
                                .setPassword(currentPassword)
                                .build()
                        val newCredential = Credential.Builder(arguments?.getString(EMAIL))
                                .setName(arguments?.getString(USERNAME))
                                .setPassword(newPassword)
                                .build()
                        credentialsClient.delete(oldCredential)
                        credentialsClient.delete(oldCredentialOnlyUsername)
                        credentialsClient.save(newCredential)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(context,
                                                context?.getString(R.string.credentials_saved_successfully),
                                                Toast.LENGTH_LONG).show()
                                    } else {
                                        val exception = it.exception
                                        if (exception is ResolvableApiException) {
                                            try {
                                                exception.startResolutionForResult(
                                                        SettingsActivity.instance, Constants.RC_REQUEST)
                                            } catch (e: IntentSender.SendIntentException) {
                                                Toast.makeText(context,
                                                        R.string.error_getting_credentials,
                                                        Toast.LENGTH_SHORT).show()
                                            }
                                        } else
                                            Toast.makeText(context,
                                                    context?.getString(R.string.credentials_save_failed),
                                                    Toast.LENGTH_LONG).show()
                                    }
                                }
                    }
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

    private fun validatePassword() : Boolean {
        return if (newPassword == confirmPassword && newPassword != currentPassword) {
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
