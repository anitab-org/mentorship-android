package org.systers.mentorship.view.fragments

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Toast
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentChangePasswordBinding
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

    private var _changePasswordBinding:FragmentChangePasswordBinding? = null
    private val changePasswordBinding get() = _changePasswordBinding!!
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

        _changePasswordBinding = FragmentChangePasswordBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.change_password))
        builder.setView(changePasswordBinding.root)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        val passwordDialog = builder.create()
        passwordDialog.setOnShowListener { passwordDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false }

        changePasswordBinding.tilConfirmPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(confirmPasswordEditable: Editable?) {
                passwordDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = confirmPasswordEditable!!.isNotEmpty()
            }

        })
        return passwordDialog
    }

    override fun onResume() {
        super.onResume()
        val passwordDialog = dialog as? AlertDialog
        passwordDialog?.setCanceledOnTouchOutside(false)
        passwordDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            changePasswordBinding.apply {
                currentPassword = tilCurrentPassword?.editText?.text.toString()
                newPassword = tilNewPassword?.editText?.text.toString()
                confirmPassword = tilConfirmPassword?.editText?.text.toString()

                tilConfirmPassword?.error = null
                tilNewPassword?.error = null
            }

            if (validatePassword()) {
                changePasswordViewModel.changeUserPassword(ChangePassword(currentPassword, newPassword))
            }
        }
    }

    private fun validatePassword(): Boolean {
        return if (!newPassword.checkPasswordSecurity()) {
            changePasswordBinding.tilNewPassword?.error = getString(R.string.error_password_too_weak)
            false
        } else if (newPassword == confirmPassword && newPassword != currentPassword) {
            true
        } else {
            if (currentPassword == newPassword) {
                changePasswordBinding.tilNewPassword?.error = getString(R.string.current_new_password_should_not_same)
            }
            if (newPassword != confirmPassword) {
                changePasswordBinding.tilConfirmPassword?.error = getString(R.string.password_not_match)
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        changePasswordViewModel.successfulUpdate.removeObservers(this)
        changePasswordViewModel.successfulUpdate.value = null
        _changePasswordBinding = null
    }
}
