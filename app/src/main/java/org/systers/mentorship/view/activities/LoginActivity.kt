package org.systers.mentorship.view.activities

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.*
import com.google.android.gms.common.api.ResolvableApiException
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.Constants.RESOLUTION_ACTIVITY_REQ_CODE
import org.systers.mentorship.viewmodels.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var credentialsClient: CredentialsClient
    private lateinit var credentialRequest: CredentialRequest

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
                    Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        credentialsClient = Credentials.getClient(this)
        credentialRequest = CredentialRequest.Builder()
                            .setPasswordLoginSupported(true)
                            .build()
        /**
         * Requesting stored user's credentials from Google Smart Lock.
         * */
        requestCredentials(credentialRequest)

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
    }

    private fun requestCredentials(credentialRequest: CredentialRequest) {
        credentialsClient.request(credentialRequest).addOnCompleteListener{task ->
            run{
                if(task.isSuccessful){
                    onCredentialRetrieved(task.result!!.credential)
                }
                var exception = task.exception
                if(exception is ResolvableApiException){
                    /**
                     * This prompts the user if the user has multiple accounts saved.
                     * */
                    try{
                        exception.startResolutionForResult(this, RESOLUTION_ACTIVITY_REQ_CODE)
                    }catch(ex : IntentSender.SendIntentException){
                        Toast.makeText(this, R.string.credentials_retrieve_failed, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, R.string.credentials_retrieve_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onCredentialRetrieved(credential: Credential) {
        var accountType = credential.accountType
        if(accountType == null) {
            username = credential.id
            password = credential.password.toString()
            loginViewModel.login(Login(username, password))
            showProgressDialog(getString(R.string.logging_in))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLUTION_ACTIVITY_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                var credential = data!!.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
                onCredentialRetrieved(credential)
            } else {
                Toast.makeText(this, R.string.credentials_retrieve_failed, Toast.LENGTH_LONG).show()
            }
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

