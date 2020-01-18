package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.Constants.RC_SIGN_IN_GOOGLE
import org.systers.mentorship.viewmodels.LoginViewModel
import org.systers.mentorship.viewmodels.SocialLoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var socialLoginViewModel: SocialLoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
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
        }

        tiPassword.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        socialLoginViewModel = ViewModelProviders.of(this).get(SocialLoginViewModel::class.java)
        socialLoginViewModel.successful.observe(this, Observer {
            if (it != null)
                if (it)
                    socialLogin()
                else {
                    hideProgressDialog()
                    if (socialLoginViewModel.message.isNotEmpty())
                        Snackbar.make(getRootView(), socialLoginViewModel.message,
                                Snackbar.LENGTH_SHORT).show()
                }
        })

        btnLoginGoogle.setOnClickListener {
            showProgressDialog(getString(R.string.logging_in))
            socialLoginViewModel.loginWithGoogle(this)
        }

        btnLoginFacebook.setOnClickListener {
            showProgressDialog(getString(R.string.logging_in))
            socialLoginViewModel.loginWithFacebook(this)
        }

        btnLoginTwitter.setOnClickListener {
            showProgressDialog(getString(R.string.logging_in))
            socialLoginViewModel.loginWithTwitter(this)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialLoginViewModel.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_SIGN_IN_GOOGLE) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    val token = account?.idToken
                    if (token != null) {
                        val credential = GoogleAuthProvider.getCredential(token, null)
                        socialLoginViewModel.firebaseAuth(credential)
                    } else {
                        hideProgressDialog()
                        Snackbar.make(getRootView(), R.string.auth_failed,
                                Snackbar.LENGTH_SHORT).show()
                    }
                } catch (e: ApiException) {
                    hideProgressDialog()
                    e.printStackTrace()
                    Snackbar.make(getRootView(), R.string.auth_failed,
                            Snackbar.LENGTH_SHORT).show()
                }
            }
        } else {
            hideProgressDialog()
            Snackbar.make(getRootView(), R.string.error_something_went_wrong,
                    Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun socialLogin() {
        hideProgressDialog()
        Toast.makeText(baseContext, "Welcome, ${socialLoginViewModel.auth.currentUser?.displayName}!",
                Toast.LENGTH_SHORT).show()
        // TODO: Add logic for social sign in
        /*
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.successful.removeObservers(this)
        loginViewModel.successful.value = null
    }

}
