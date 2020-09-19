package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.utils.checkPasswordSecurity
import org.systers.mentorship.viewmodels.SignUpViewModel

/**
 * This activity will let the user to sign up into the system using name, username,
 * email and password.
 */
class SignUpActivity : BaseActivity() {

    private val signUpViewModel by lazy {
        ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }
    private lateinit var name: String
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmedPassword: String
    private var isAvailableToMentor: Boolean = false
    private var needsMentoring: Boolean = false
    private var isAvailableForBoth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpViewModel.successful.observe(this, Observer { successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, signUpViewModel.message, Toast.LENGTH_LONG).show()
                    navigateToLoginActivity()
                } else {
                    Snackbar.make(getRootView(), signUpViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        tvTC.movementMethod = LinkMovementMethod.getInstance()

        btnSignUp.setOnClickListener {

            name = tiName.editText?.text.toString()
            username = tiUsername.editText?.text.toString()
            email = tiEmail.editText?.text.toString()
            password = tiPassword.editText?.text.toString()
            confirmedPassword = tiConfirmPassword.editText?.text.toString()
            needsMentoring = cbMentee.isChecked //old name but works
            isAvailableToMentor = cbMentor.isChecked //old name but works
            isAvailableForBoth = cbBoth.isChecked

            if (validateDetails()) {
                val requestData = Register(name, username, email, password, true, needsMentoring, isAvailableToMentor)
                signUpViewModel.register(requestData)
                showProgressDialog(getString(R.string.signing_up))
            }
        }
        btnLogin.setOnClickListener {
            navigateToLoginActivity()
        }
        cbTC.setOnCheckedChangeListener { _, b ->
            btnSignUp.isEnabled = b
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        signUpViewModel.successful.removeObservers(this)
        signUpViewModel.successful.value = null
    }

    private fun validateDetails(): Boolean {
        var isValid = true
        if (name.isBlank()) {
            tiName.error = getString(R.string.error_empty_name)
            isValid = false
        } else {
            tiName.error = null
        }

        if (username.isBlank()) {
            tiUsername.error = getString(R.string.error_empty_username)
            isValid = false
        } else {
            tiUsername.error = null
        }

        if (email.isBlank()) {
            tiEmail.error = getString(R.string.error_empty_email)
            isValid = false
        } else {
            tiEmail.error = null
        }

        if (password.isBlank()) {
            tiPassword.error = getString(R.string.error_empty_password)
            isValid = false
        } else if (!password.checkPasswordSecurity()) {
            tiPassword.error = getString(R.string.error_password_too_weak)
            isValid = false
        } else {
            tiPassword.error = null
        }

        if (password != confirmedPassword) {
            tiConfirmPassword.error = getString(R.string.error_not_matching_passwords)
            isValid = false
        } else if (confirmedPassword.isBlank()) {
            tiConfirmPassword.error = getString(R.string.error_empty_password_confirmation)
            isValid = false
        } else {
            tiConfirmPassword.error = null
        }

        if (!needsMentoring && !isAvailableToMentor && !isAvailableForBoth) {
            isValid = false
            cbMentee.requestFocus()
            cbMentor.requestFocus()
            cbBoth.requestFocus()
            tvNoteSignUp.visibility = View.VISIBLE
        } else if (isAvailableForBoth) {
            needsMentoring = true
            isAvailableToMentor = true
            tvNoteSignUp.visibility = View.GONE
        } else {
            tvNoteSignUp.visibility = View.GONE
        }

        return isValid
    }

    private fun navigateToLoginActivity() {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
