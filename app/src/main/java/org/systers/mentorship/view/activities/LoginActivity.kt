package org.systers.mentorship.view.activities

import androidx.lifecycle.Observer
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialRequest
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.CredentialsClient
import com.google.android.gms.common.api.ResolvableApiException
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.RC_SAVE
import org.systers.mentorship.utils.CountingIdlingResourceSingleton
import org.systers.mentorship.viewmodels.LoginViewModel
import java.lang.Exception

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var mCredentialsClient: CredentialsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupCredentialsManager()

        etUsername.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)

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
                CountingIdlingResourceSingleton.decrement()
            }
        })

        btnLogin.setOnClickListener {
            login()
        }

        btnSignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        tiPassword.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        try {
            val tokenExpiredVal = intent.extras!!.getInt(Constants.TOKEN_EXPIRED_EXTRA)
            if (tokenExpiredVal == 0)
                Snackbar.make(getRootView(), "Session token expired, please login again", Snackbar.LENGTH_LONG).show()
        }catch (exception: Exception){}

        checkFieldsForEmptyValues()
    }

    private fun checkFieldsForEmptyValues(){
        val editText1: String? = etUsername.text.toString()
        val editText2: String? = etPassword.text.toString()

        /**
         * Disables the button if one of the EditText field is empty
         */
        btnLogin.isEnabled = !(editText1.equals("") || editText2.equals(""))
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            checkFieldsForEmptyValues()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
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
        CountingIdlingResourceSingleton.increment()
        username = tiUsername.editText?.text.toString().trim()
        password = tiPassword.editText?.text.toString().trim()
        saveCredentials(username, password)
    }

    private fun setupCredentialsManager() {
        mCredentialsClient = Credentials.getClient(this)
        val mCredentialsRequest = CredentialRequest.Builder().setPasswordLoginSupported(true).build()
        mCredentialsClient.request(mCredentialsRequest).addOnCompleteListener {
            if (it.isSuccessful) {
                onCredentialsRetrieved(it.result?.credential!!)
            } else {
                Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onCredentialsRetrieved(credential: Credential) {
        username = credential.id
        password = credential.password.toString()
        tiUsername.editText?.setText(username)
        tiPassword.editText?.setText(password)
    }

    private fun saveCredentials(username: String, password: String) {
        val credential = Credential.Builder(username).setPassword(password).build()
        mCredentialsClient.save(credential).addOnCompleteListener {
            val e  = it.exception
            if (e is ResolvableApiException) {
                // Try to resolve the save request. This will prompt the user if
                // the credential is new.
                try {
                    e.startResolutionForResult(this, RC_SAVE)
                } catch (exception: IntentSender.SendIntentException) {
                    // Could not resolve the request
                    Log.i("Save", e.message.toString())
                    Toast.makeText(this@LoginActivity, "Save failed ${e.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.i("Save", e?.message.toString())
                // Request has no resolution
                // simply login the user
                loginUser()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SAVE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(this@LoginActivity.getRootView(), getString(R.string.credentials_saved), Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(this@LoginActivity.getRootView(), getString(R.string.save_canceled_by_user), Snackbar.LENGTH_SHORT).show()
            }
            loginUser()
        }
    }

    private fun loginUser() {
        if (validateCredentials()) {
            loginViewModel.login(Login(username, password))
            showProgressDialog(getString(R.string.logging_in))
        } else {
            CountingIdlingResourceSingleton.decrement()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }
}

