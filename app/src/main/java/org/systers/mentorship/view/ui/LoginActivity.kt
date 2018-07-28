package org.systers.mentorship.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.view.base.BaseActivity
import org.systers.mentorship.viewmodel.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel  = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.error.value = null
        loginViewModel.error.observe(this, Observer {
            error ->
            hideProgressDialog()
            if (!error.isNullOrEmpty()) {
                Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
            }
        })

        btnLogin.setOnClickListener {
            //TODO: Get credentials from views
            loginViewModel.login(LoginRequest("12345678", "12345678"))
            showProgressDialog(getString(R.string.logging_in))
        }
    }
}
