package org.systers.mentorship.view.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.viewmodels.LoginViewModel


/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private val loginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        ForgetPassword.setOnClickListener {
            intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
            finish()

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
    }

    private fun forgotpassword(email:EditText)    {
        if(email.text.toString().isEmpty()) {
            return
        }
        val auth: FirebaseAuth =  FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Reset Password link sent", Toast.LENGTH_SHORT).show()
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
        username = tiUsername.editText?.text.toString().trim()
        password = tiPassword.editText?.text.toString().trim()
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

