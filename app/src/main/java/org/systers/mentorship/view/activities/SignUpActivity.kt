package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.utils.CountingIdlingResourceSingleton
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
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

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
                CountingIdlingResourceSingleton.decrement()
            }
        })

        tvTC.movementMethod = LinkMovementMethod.getInstance()
        fun View.hideKeyboard(){
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        }

        contentView.setOnClickListener {
            it.hideKeyboard()
        }
        btnSignUp.setOnClickListener {

            name = tiName.editText?.text.toString()
            username = tiUsername.editText?.text.toString()
            email = tiEmail.editText?.text.toString()
            password = tiPassword.editText?.text.toString()
            confirmedPassword = tiConfirmPassword.editText?.text.toString()
            needsMentoring = cbMentee.isChecked //old name but works
            isAvailableToMentor = cbMentor.isChecked //old name but works
            isAvailableForBoth = cbBoth.isChecked

            CountingIdlingResourceSingleton.increment()
            if (validateDetails()) {
                val requestData = Register(name, username, email, password, true, needsMentoring, isAvailableToMentor)
                signUpViewModel.register(requestData)
                showProgressDialog(getString(R.string.signing_up))
            } else CountingIdlingResourceSingleton.decrement()
        }
        btnLogin.setOnClickListener {
            navigateToLoginActivity()
        }
        cbTC.setOnCheckedChangeListener { _, b ->
            btnSignUp.isEnabled = b
        }

        validateDetailsOnRuntime()
    }

    override fun onDestroy() {
        super.onDestroy()
        signUpViewModel.successful.removeObservers(this)
        signUpViewModel.successful.value = null
    }

    private fun validateDetailsOnRuntime(){
        tiEmail.editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(!s.toString().matches(emailPattern.toRegex())){
                    tiEmail.editText?.error=getString(R.string.valid_error)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        tiConfirmPassword.editText?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(!tiPassword.editText?.text.toString().contentEquals(s.toString())){
                    tiConfirmPassword.editText?.error = getString(R.string.password_not_match)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
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
