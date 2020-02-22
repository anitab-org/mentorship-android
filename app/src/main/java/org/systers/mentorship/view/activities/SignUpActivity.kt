package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.viewmodels.SignUpViewModel


/**
 * This activity will let the user to sign up into the system using name, username,
 * email and password.
 */
class SignUpActivity : BaseActivity() {
    private val TAG = this::class.java.simpleName
    private val RC_SAVE_CREDENTIALS = 1

    private val signUpViewModel by lazy {
        ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    private val credentialsClient = Credentials.getClient(MentorshipApplication.getContext())

    private lateinit var name: String
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmedPassword: String
    private var isAvailableToMentor: Boolean = false
    private var needsMentoring: Boolean = false

    private val listener = object : OnCompleteListener<Void> {
        override fun onComplete(task: Task<Void>) {
            if (task.isSuccessful) {
                signUpViewModel.smartLockSuccessful.value = true
            } else {
                val e = task.exception
                if(e is ResolvableApiException) {
                    try {
                        e.startResolutionForResult(this@SignUpActivity, RC_SAVE_CREDENTIALS)
                    } catch (e: IntentSender.SendIntentException) {
                        // Could not resolve the request
                        Log.e(TAG, "Failed to send resolution.", e)
                        signUpViewModel.smartLockSuccessful.value = false
                    }
                } else {
                    Log.e(TAG, "Unresolvable exception.", e)
                    signUpViewModel.smartLockSuccessful.value = false
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpViewModel.signUpData.observe(this, Observer {
            hideProgressDialog()
            if (it != null) {
                val credential = Credential.Builder(it.email).setName(it.name)
                    .setPassword(it.password).build()

                credentialsClient.save(credential).addOnCompleteListener(listener)
                Toast.makeText(this, signUpViewModel.message, Toast.LENGTH_LONG).show()
            }
            else {
                Snackbar.make(getRootView(), signUpViewModel.message, Snackbar.LENGTH_LONG)
                    .show()
            }
        })

        signUpViewModel.smartLockSuccessful.observe(this, Observer {
            if (it != null) {
                if (it) {
                    Log.d(TAG, "Credential save: OK")
                    navigateToLoginActivity()
                } else {
                    Log.d(TAG, "Credential save: FAILED")
                    navigateToLoginActivity()
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
            needsMentoring = cbMentee.isChecked //old name but works
            isAvailableToMentor = cbMentor.isChecked //old name but works

            if (validateDetails()) {
                val requestData = Register(name, username, email, password, true, needsMentoring, isAvailableToMentor)
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SAVE_CREDENTIALS) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "Credential save: OK")
                signUpViewModel.smartLockSuccessful.value = true
            } else {
                Log.e(TAG, "Credential save: Canceled by user")
                signUpViewModel.smartLockSuccessful.value = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        signUpViewModel.signUpData.removeObservers(this)
        signUpViewModel.signUpData.value = null
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

        return isValid
    }

    private fun navigateToLoginActivity() {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
