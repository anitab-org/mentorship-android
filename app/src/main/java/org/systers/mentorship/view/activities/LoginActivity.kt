package org.systers.mentorship.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Email
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.viewmodels.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, R.string.logging_successful, Toast.LENGTH_LONG)
                            .show()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (loginViewModel.message.equals(getString(R.string.verify_email))) {
                        Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_INDEFINITE)
                                .setAction(getString(R.string.resend_email)) {
                                    loginViewModel.resendEmail(Email(tiUsername.editText?.text.toString()))
                                }.show()
                    } else {
                        Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG)
                                .show()
                    }
                }
            }
        })

        loginViewModel.resendEmailSuccessful.observe(this, Observer {
            resendEmailSuccessful ->
            hideProgressDialog()
            if (resendEmailSuccessful != null) {
                if (resendEmailSuccessful) {
                    Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        btnLogin.setOnClickListener {
            username = tiUsername.editText?.text.toString()
            password = tiPassword.editText?.text.toString()
            if (validateCredentials()) {
                loginViewModel.login(Login(username, password))
                showProgressDialog(getString(R.string.logging_in))
            }
        }

        btnSignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateCredentials() : Boolean {
        var validCredentials = true
        if (username.isBlank()) {
            tiUsername.error = getString(R.string.error_empty_username)
            validCredentials = false
        } else {
            tiUsername.error = null
        }
        if (password.isBlank()) {
            tiPassword.error = getString(R.string.error_empty_password)
            validCredentials = false
        } else {
            tiPassword.error = null
        }
        return validCredentials
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }
}
