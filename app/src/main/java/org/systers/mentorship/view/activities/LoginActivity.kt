package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialRequest
import com.google.android.gms.auth.api.credentials.CredentialRequestResponse
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.viewmodels.LoginViewModel


/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {
    private val TAG = this::class.java.simpleName
    private val RC_READ_CREDENTIALS = 2

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    private val credentialsClient = Credentials.getClient(MentorshipApplication.getContext())
    private var currentCredential: Credential? = null

    private val requestListener = OnCompleteListener<CredentialRequestResponse> { task ->
        if (task.isSuccessful) {
            task.result?.credential?.let {
                onCredentialRetrieved(it)
                return@OnCompleteListener
            }
            Log.d(TAG, "task.result or task.result.credential is null")

        } else {
            val e = task.exception
            if (e is ResolvableApiException) {
                resolveResult(e, RC_READ_CREDENTIALS)
            } else if (e is ApiException) {
                // The user must create an account or sign in manually.
                val code = e.statusCode
                Log.e(TAG, "Unsuccessful credential request. Code $code", e)
            }
        }
    }

    private fun onCredentialRetrieved(credential: Credential) {
        if (credential.accountType == null) {
            currentCredential = credential
            val id = credential.id // This is a real username
            val username = credential.name // This is usually null
            val password = credential.password
            Log.d(TAG, "ID: $id, Username: $username, Password: $password")

            loginViewModel.usernameText.value = id
            loginViewModel.passwordText.value = password
        }
    }

    private fun resolveResult(resolvableApiException: ResolvableApiException, requestCode: Int) {
        try {
            resolvableApiException.startResolutionForResult(this, requestCode)
        } catch (e: SendIntentException) {
            Log.e(TAG, "Failed to send resolution.", e)
        }
    }

    private fun deleteCredentials(credential: Credential) {
        val deleteListener = OnCompleteListener<Void> { task ->
            if (task.isSuccessful) {
                val id = credential.id // This is a real username
                val username = credential.name // This is usually null
                val password = credential.password
                Log.d(TAG, "Credential id: $id, Username: $username, Password: $password DELETED.")
            } else {
                Log.e(TAG, "Error while deleting credentials.")
            }
        }

        credentialsClient.delete(credential).addOnCompleteListener(deleteListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.successful.observe(this, Observer { login ->
            hideProgressDialog()
            if (login != null) {
                Toast.makeText(this, R.string.logging_successful, Toast.LENGTH_LONG).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d(TAG, "successful false")
                // If credentials used to login are the same as those stored in Smart Lock
                // and are wrong, we want to delete them
                currentCredential?.let { credential ->
                    if ((credential.id == etUsername.text.toString()) and (credential.password == etPassword.text.toString())) {
                        deleteCredentials(credential)
                    }

                }
                Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG).show()
            }
        })

        val request = CredentialRequest.Builder().setPasswordLoginSupported(true).build()
        credentialsClient.request(request).addOnCompleteListener(requestListener)

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

        loginViewModel.usernameText.observe(this, Observer {
            tiUsername.editText?.setText(it)
        })

        loginViewModel.passwordText.observe(this, Observer {
            tiPassword.editText?.setText(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_READ_CREDENTIALS) {
            if (resultCode == Activity.RESULT_OK) {
                val credential: Credential = data!!.getParcelableExtra(Credential.EXTRA_KEY)
                onCredentialRetrieved(credential)
            } else {
                Log.e(TAG, "Credential Read: Failed. Result code: $resultCode")
            }
        }
    }

    private fun validateCredentials(): Boolean {
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
        if (validateCredentials()) {
            loginViewModel.login(Login(username, password))
            showProgressDialog(getString(R.string.logging_in))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }
}

