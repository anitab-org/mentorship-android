package org.systers.mentorship.view.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.viewmodel.LoginViewModel

/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel  = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {
            //TODO: Get credentials from views
            loginViewModel.login(LoginRequest("12345678", "12345678"))
        }
    }
}
