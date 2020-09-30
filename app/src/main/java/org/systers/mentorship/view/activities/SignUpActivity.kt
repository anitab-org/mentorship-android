package org.systers.mentorship.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.viewmodels.SignUpViewModel
import java.util.regex.Pattern

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

        tiName.editText?.addTextChangedListener(AddListenerOnTextChange(this,
                tiName.editText,
                getString(R.string.name),
                Constants.NAME_MIN_LENGTH,
                Constants.NAME_MAX_LENGTH,
                getString(R.string.regex_name),
                getString(R.string.error_invalid_name),
                cbTC))
        tiUsername.editText?.addTextChangedListener(AddListenerOnTextChange(this,
                tiUsername.editText,
                getString(R.string.username),
                Constants.USERNAME_MIN_LENGTH,
                Constants.USERNAME_MAX_LENGTH,
                getString(R.string.regex_username),
                getString(R.string.error_invalid_username),
                cbTC))
        tiEmail.editText?.addTextChangedListener(AddListenerOnTextChange(this,
                tiEmail.editText,
                getString(R.string.email),
                Constants.EMAIL_MIN_LENGTH,
                Constants.EMAIL_MAX_LENGTH,
                getString(R.string.regex_email),
                getString(R.string.error_invalid_email),
                cbTC))
        tiPassword.editText?.addTextChangedListener(AddListenerOnTextChange(this,
                tiPassword.editText,
                getString(R.string.password),
                Constants.PASSWORD_MIN_LENGTH,
                Constants.PASSWORD_MAX_LENGTH,
                getString(R.string.regex_password),
                getString(R.string.error_invalid_password),
                cbTC))
        tiConfirmPassword.editText?.addTextChangedListener(AddListenerOnTextChange(this,
                tiConfirmPassword.editText,
                getString(R.string.confirm_password),
                Constants.PASSWORD_MIN_LENGTH,
                Constants.PASSWORD_MAX_LENGTH,
                getString(R.string.regex_password),
                getString(R.string.error_invalid_password),
                cbTC))

        btnSignUp.setOnClickListener {

            val requestData = Register(name, username, email, password, true, needsMentoring, isAvailableToMentor)
            signUpViewModel.register(requestData)
            showProgressDialog(getString(R.string.signing_up))
        }
        btnLogin.setOnClickListener {
            navigateToLoginActivity()
        }
        cbTC.setOnCheckedChangeListener { _, b ->

            name = tiName.editText?.text.toString()
            username = tiUsername.editText?.text.toString()
            email = tiEmail.editText?.text.toString()
            password = tiPassword.editText?.text.toString()
            confirmedPassword = tiConfirmPassword.editText?.text.toString()
            needsMentoring = cbMentee.isChecked //old name but works
            isAvailableToMentor = cbMentor.isChecked //old name but works
            isAvailableForBoth = cbBoth.isChecked

            if (validateDetails()) {
                btnSignUp.isEnabled = b
            } else {
                cbTC.isChecked = false
            }
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
            tiName.editText?.error = getString(R.string.error_empty_field, getString(R.string.name))
            isValid = false
        }

        if (username.isBlank()) {
            tiUsername.editText?.error = getString(R.string.error_empty_field, getString(R.string.username))
            isValid = false
        }

        if (email.isBlank()) {
            tiEmail.editText?.error = getString(R.string.error_empty_field, getString(R.string.email))
            isValid = false
        }

        if (password.isBlank()) {
            tiPassword.editText?.error = getString(R.string.error_empty_field, getString(R.string.password))
            isValid = false
        }

        if (confirmedPassword.isBlank()) {
            tiConfirmPassword.editText?.error = getString(R.string.error_empty_field, getString(R.string.confirm_password))
            isValid = false
        }

        if (password != confirmedPassword) {
            tiConfirmPassword.editText?.error = getString(R.string.error_not_matching_passwords)
            isValid = false
        }

        if (tiName.editText?.error != null || tiUsername.editText?.error != null || tiEmail.editText?.error != null ||
                tiPassword.editText?.error != null || tiConfirmPassword.editText?.error != null) {
            isValid = false
        }

        when {
            !needsMentoring && !isAvailableToMentor && !isAvailableForBoth -> {
                cbMentee.requestFocus()
                cbMentor.requestFocus()
                cbBoth.requestFocus()
                tvNoteSignUp.visibility = View.VISIBLE
                isValid = false
            }
            isAvailableForBoth -> {
                needsMentoring = true
                isAvailableToMentor = true
                tvNoteSignUp.visibility = View.GONE
            }
            else -> tvNoteSignUp.visibility = View.GONE
        }

        return isValid
    }

    private fun navigateToLoginActivity() {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private class AddListenerOnTextChange(context: Context, editText: EditText?, fieldName: String, minLength: Int, maxLength: Int, regex: String, invalidFieldErrMsg: String, cbTC: AppCompatCheckBox) : TextWatcher {

        private var mContext: Context
        private var mEditText: EditText?
        private var mFieldName: String
        private var mMinLength: Int
        private var mMaxLength: Int
        private var mRegex: String
        private var mInvalidFieldErrMsg: String
        private var mCbTC:AppCompatCheckBox

        init {
            this.mContext = context
            this.mEditText = editText
            this.mFieldName = fieldName
            this.mMinLength = minLength
            this.mMaxLength = maxLength
            this.mRegex = regex
            this.mInvalidFieldErrMsg = invalidFieldErrMsg
            this.mCbTC = cbTC
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            val length = s.toString().length
            val errMsg: String?
            when {
                length !in mMinLength..mMaxLength -> errMsg = mContext.getString(R.string.error_invalid_length, mFieldName, mMinLength, mMaxLength)
                !Pattern.matches(mRegex, s.toString()) -> errMsg = mInvalidFieldErrMsg
                else -> errMsg = null
            }
            if (errMsg != null) {
                mCbTC.isChecked = false
            }
            mEditText?.error = errMsg
        }
    }
}
