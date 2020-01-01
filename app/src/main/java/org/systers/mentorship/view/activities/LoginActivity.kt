package org.systers.mentorship.view.activities

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.Credential
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.RC_REQUEST
import org.systers.mentorship.viewmodels.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    private var saveCredentials = true
    /*
        we need to save credentials and then compare them to actual credentials
        so that if the user changes them, they will be saved to Smart Lock
    */
    private var savedName = ""
    private var savedPassword = ""

    companion object {
        lateinit var instance: LoginActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        loginViewModel.getCredentials()
        loginViewModel.successfulCredentials.observe(this, Observer {
            if (it) {
                savedName = loginViewModel.username
                savedPassword = loginViewModel.password
                tiUsername.editText?.setText(savedName)
                tiPassword.editText?.setText(savedPassword)
                saveCredentials = false
            }
        })

        loginViewModel.successful.observe(this, Observer { successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, R.string.logging_successful, Toast.LENGTH_LONG)
                            .show()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        btnLogin.setOnClickListener {
           login()
        }

        btnSignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        tiPassword.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        instance = this
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

    private fun login() {
        username = tiUsername.editText?.text.toString()
        password = tiPassword.editText?.text.toString()
        if (savedName != username || savedPassword != password)
            saveCredentials = true
        if (validateCredentials()) {
            loginViewModel.login(Login(username, password), saveCredentials)
            showProgressDialog(getString(R.string.logging_in))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_REQUEST ) {
                val credential = data?.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
                savedName = credential?.name ?: ""
                savedPassword = credential?.password ?: ""
                tiUsername.editText?.setText(savedName)
                tiPassword.editText?.setText(savedPassword)
                saveCredentials = false
            }
            if (requestCode == Constants.RC_SAVE)
                Toast.makeText(this, R.string.credentials_saved_successfully,
                        Toast.LENGTH_SHORT).show()
        }
    }

}

