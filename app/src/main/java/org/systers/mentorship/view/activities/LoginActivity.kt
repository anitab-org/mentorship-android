package org.systers.mentorship.view.activities

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.*
import com.google.android.gms.common.api.ResolvableApiException
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.viewmodels.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
private const val TAG = "LoginActivity"
private const val RC_SAVE = 1
private const val RC_READ = 2
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    private lateinit var mCredentialsClient: CredentialsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        // read credentials
        mCredentialsClient = Credentials.getClient(this)
        val mCredentialRequest = CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build()

        mCredentialsClient.request(mCredentialRequest).addOnCompleteListener {task -> run {
                if (task.isSuccessful){
                    onCredentialRetrieved(task.result!!.credential)
                }
                else {
                    val exception = task.exception
                    if (exception is ResolvableApiException){
                        Log.i(TAG, "prompt user if multiple saved credentials")
                        try {
                            Log.i(TAG, "starting resolution")
                            exception.startResolutionForResult(this, RC_READ)
                        }
                        catch (e: IntentSender.SendIntentException) {
                            Log.e(TAG, "Failed to send resolution.", e)
                            Toast.makeText(
                                    this, "Credential Read Failed", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else {
                        Toast.makeText(
                                this, "Credential Read Failed", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

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
            //save credentials
            val credential = Credential.Builder(username)
                    .setPassword(password)
                    .build()
            mCredentialsClient.save(credential).addOnCompleteListener {task -> run {
                    if (task.isSuccessful){
                        Log.d(TAG, "SAVE: OK")
                        Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val exception = task.exception
                        if (exception is ResolvableApiException){
                            //prompt user if credential is new
                            try {
                                Log.i(TAG, "starting resolution")
                                exception.startResolutionForResult(this, RC_SAVE)
                            }
                            catch (e: IntentSender.SendIntentException) {
                                Log.e(TAG, "Failed to send resolution.", e)
                                Toast.makeText(
                                        this,
                                        "Credential Save failed",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        else{
                            // Request has no resolution
                            Toast.makeText(
                                    this, "Credential Save failed", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            loginViewModel.login(Login(username, password))
            showProgressDialog(getString(R.string.logging_in))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }

    private fun onCredentialRetrieved(credential: Credential) {
        val accountType = credential.accountType
        if (accountType == null) { //null for userId and password format
            // Sign the user in with information from the Credential.
            loginViewModel.login(Login(credential.id, credential.password!!))
            showProgressDialog(getString(R.string.logging_in))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SAVE) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "SAVE: OK")
                Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "SAVE: Canceled by user")
            }
        }
        if (requestCode == RC_READ) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "Credential Read: OK")
                val credential = data!!.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
                onCredentialRetrieved(credential)
            } else {
                Log.e(TAG, "Credential Read: NOT OK")
                Toast.makeText(
                        this, "Credential Read Failed", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

