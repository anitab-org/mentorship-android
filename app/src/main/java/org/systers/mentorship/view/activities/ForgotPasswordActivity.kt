package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.ForgotPassword
import org.systers.mentorship.viewmodels.ForgotPasswordViewModel

/*This activity implements forgot password functionality
  which helps user to receive an OTP for resetting password on
  the registered email address*/

class ForgotPasswordActivity : BaseActivity() {

    private val ForgotPasswordViewModel by lazy {
        ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
    }

    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        /*this observes the successful variable
         on ForgotPassword ViewModel*/

        ForgotPasswordViewModel.successful.observe(this, Observer { successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, ForgotPasswordViewModel.message, Toast.LENGTH_LONG).show()

                } else {
                    Snackbar.make(getRootView(), ForgotPasswordViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        /*This triggers the process for making retrofit
         call for OTP generation for the input Email calling
         forgotPassword function in ForgotPasswordViewModel*/

        btnSend.setOnClickListener {

            email = tiRegisteredEmail.editText?.text.toString()

            if (validateEmail()) {
                val requestOtp = ForgotPassword(email)
                ForgotPasswordViewModel.forgotPassword(requestOtp)

            }
        }
    }

    fun validateEmail():Boolean {

        var isValid = true

        if (email.isBlank()) {
            tiRegisteredEmail.error = getString(R.string.error_empty_email)
            isValid = false
        } else {
            tiRegisteredEmail.error = null
        }

        return isValid
    }
}


