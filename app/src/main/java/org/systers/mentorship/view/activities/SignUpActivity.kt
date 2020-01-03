package org.systers.mentorship.view.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.utils.Constants.DATE_FORMAT
import org.systers.mentorship.utils.Constants.MAX_BIRTHDAY_YEAR
import org.systers.mentorship.utils.Constants.MIN_AGE
import org.systers.mentorship.viewmodels.SignUpViewModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * This activity will let the user to sign up into the system using name, username,
 * email and password.
 */
class SignUpActivity : BaseActivity() {

    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var name: String
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmedPassword: String
    private var isAvailableToMentor: Boolean = false
    private var needsMentoring: Boolean = false
    private var date = ""
    private val calendar = Calendar.getInstance()
    private val maxDate by lazy {
        calendar.add(Calendar.YEAR, MIN_AGE)
        return@lazy calendar.timeInMillis
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
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
            needsMentoring = cbMentee.isChecked
            isAvailableToMentor = cbMentor.isChecked

            if (validateDetails()) {
                val requestData = Register(name, username, email, password,
                        true, needsMentoring, isAvailableToMentor,
                        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(date).time.toFloat())
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

        //needed to auto-insert slashes(/)
        tiDateOfBirth.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    //if user entered/deleted day or month, add  or remove /
                    if (s.length == 2 || s.length == 5)
                        if (s.length > date.length)
                            tiDateOfBirth.editText?.text?.append("/")
                        else {
                            tiDateOfBirth.editText?.setText(s.dropLast(1))
                            tiDateOfBirth.editText?.setSelection(s.length - 1)
                        }
                    date = tiDateOfBirth.editText?.text.toString()
                }
            }
        })

        val d = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(year, month, day)
            val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            tiDateOfBirth.editText?.setText(sdf.format(calendar.time))
        }

        ivCalendar.setOnClickListener {
            val validateDate = validateDate(false)
            val day = if (validateDate) date.take(2).toInt() else calendar.get(Calendar.DAY_OF_MONTH)
            val month = if (validateDate) date.substring(3, 5).toInt().dec() else calendar.get(Calendar.MONTH)
            val year = if (validateDate) date.takeLast(4).toInt() else calendar.get(Calendar.YEAR)

            val dialog = DatePickerDialog(this, d, year, month, day)
            dialog.datePicker.maxDate = maxDate
            dialog.show()
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
        } else {
            tiPassword.error = null
        }

        if (password != confirmedPassword) {
            tiConfirmPassword.error = getString(R.string.error_not_matching_passwords)
            isValid = false
        } else {
            tiConfirmPassword.error = null
        }

        return validateDate(true) && isValid
    }

    private fun validateDate(showErrors: Boolean): Boolean {
        var isValid = true
        date = tiDateOfBirth.editText?.text.toString()

        if (date.length < 10) {
            if (showErrors) tiDateOfBirth.error = getString(R.string.enter_whole_date)
            isValid = false
        } else {
            if (showErrors) tiDateOfBirth.error = null

            if (date.takeLast(4).toInt() < MAX_BIRTHDAY_YEAR) {
                if (showErrors) tiDateOfBirth.error = getString(R.string.entered_incorrect_date)
                isValid = false
            }

            try {
                val dateFormat: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                dateFormat.isLenient = false
                val d = dateFormat.parse(date)
                if (maxDate < d.time) {
                    if (showErrors) tiDateOfBirth.error = getString(R.string.min_age_error, -MIN_AGE)
                    isValid = false
                }
            } catch (e: ParseException) {
                if (showErrors) tiDateOfBirth.error = getString(R.string.entered_incorrect_date)
                isValid = false
            }
        }
        return isValid
    }

    private fun navigateToLoginActivity() {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
