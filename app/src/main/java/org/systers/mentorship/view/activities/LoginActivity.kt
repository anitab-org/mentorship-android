package org.systers.mentorship.view.activities

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.systers.mentorship.R
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.responses.GithubAccessToken
import org.systers.mentorship.remote.services.GithubService
import org.systers.mentorship.utils.Constants.RC_GOOGLE_SIGNIN
import org.systers.mentorship.viewmodels.LoginViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * This activity will let the user to login using username/email and password.
 */
@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var username: String
    private lateinit var password: String

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var callbackManager: CallbackManager
    private lateinit var twitterAuthClient: TwitterAuthClient

    private lateinit var intentActivity: String

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


        btnLogin.setOnClickListener {
           login()
        }

        btnLoginWithOtherAccounts.setOnClickListener {
            updateUI(0.2f,1f)
        }

        civGoogle.setOnClickListener {
            googleSignIn()
        }

        civFacebook.setOnClickListener {
            facebookSignIn()
        }

        civTwitter.setOnClickListener {
            twitterSignIn()
        }

        civGithub.setOnClickListener {
            githubSignIn()
        }

        btnSignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        ltLoginActivity.setOnClickListener {
            updateUI(1f,0f)
        }

        tiPassword.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }
    }

    private fun updateUI(ltAlpha: Float, btnLoginAlpha: Float){

        ltLoginActivity.animate().alpha(ltAlpha).duration = 500
        ltOtherLoginOptions.animate().alpha(btnLoginAlpha).duration = 1000
    }

    override fun onResume() {
        super.onResume()

        /**
         * The callback from Github authentication page, which catches the code returned by Github.
         * Here, the code returned is verified with the current client details.
         * If sent by a third-party, the user is not authenticated.
         * */
        val uri = intent.data
        if (uri!=null) {

            val code = uri.getQueryParameter("code")
            val clientId = getString(R.string.github_client_id)
            val clientSecret = getString(R.string.github_client_secret)

            val githubRetrofit = Retrofit.Builder()
                                            .baseUrl(getString(R.string.url_github))
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build()

            val githubService = githubRetrofit.create(GithubService::class.java)

            val accessTokenCall = githubService.loginWithGithub(clientId, clientSecret, code)

            accessTokenCall.enqueue(object: retrofit2.Callback<GithubAccessToken>{

                override fun onResponse(call: Call<GithubAccessToken>, response: Response<GithubAccessToken>) {

                    Toast.makeText(applicationContext, getString(R.string.logging_successful) ,Toast.LENGTH_LONG).show()
                    updateUI(1f, 0f)
                }

                override fun onFailure(call: Call<GithubAccessToken>, throwable: Throwable) {
                    Toast.makeText(applicationContext,throwable.message,Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun googleSignIn(){
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                                                         .requestEmail()
                                                                          .build()
        googleApiClient = GoogleApiClient.Builder(this)
                                         .enableAutoManage(this,this)
                                         .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                                         .build()

        val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(intent, RC_GOOGLE_SIGNIN)
    }

    private fun facebookSignIn(){

        intentActivity = getString(R.string.facebook)

        callbackManager = CallbackManager.Factory.create()

        // Handling the login result from Facebook
        LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult>{

            override fun onSuccess(result: LoginResult) {

                val accessToken = result.accessToken
                val graphRequest = GraphRequest.newMeRequest(accessToken) { `object`, _ ->

                    try {
                        val firstName = `object`?.getString(getString(R.string.first_name))
                        val lastName = `object`?.getString(getString(R.string.last_name))
                        Toast.makeText(applicationContext, getString(R.string.successful_login) + "$firstName $lastName", Toast.LENGTH_LONG).show()
                        updateUI(1f, 0f)
                    } catch (ex: JSONException) {

                        ex.printStackTrace()
                        Toast.makeText(applicationContext, ex.message, Toast.LENGTH_LONG).show()
                    }
                }

                /**
                 * Setting up the parameter fields to be fetched from the user profile.
                 * */
                val parameters = Bundle()
                parameters.putString("fields", getString(R.string.first_name) + "," + getString(R.string.last_name))
                graphRequest.parameters = parameters
                graphRequest.executeAsync()
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
                error.printStackTrace()
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
            }
        })

        // Initiating Facebook SignIn
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"))
    }

    private fun twitterSignIn(){

        intentActivity = getString(R.string.twitter)

        // Creating a custom Twitter Configuration
        var config = TwitterConfig.Builder(this)
                                                .logger(DefaultLogger(Log.DEBUG))
                                                .twitterAuthConfig(TwitterAuthConfig(getString(R.string.CONSUMER_KEY), getString(R.string.CONSUMER_SECRET)))
                                                .debug(true)
                                                .build()
        Twitter.initialize(config)
        twitterAuthClient = TwitterAuthClient()

        // Initiating and handling the result from Twitter SignIn
        twitterAuthClient.authorize(this, object: Callback<TwitterSession>(){
            override fun success(result: Result<TwitterSession>) {

                val session = TwitterCore.getInstance().sessionManager.activeSession
                val username = session.userName
                Toast.makeText(applicationContext, getString(R.string.successful_login) + username, Toast.LENGTH_LONG).show()
                updateUI(1f, 0f)
            }

            override fun failure(exception: TwitterException) {
                Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun githubSignIn(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_github_oauth_login,
                                                             getString(R.string.github_client_id),
                                                             getString(R.string.github_oauth_redirect_uri))))
        startActivity(intent)
    }

    override fun onConnectionFailed(p0: ConnectionResult) { }

    /**
     * This method handles the results from Google, Facebook and Twitter sign in activities.
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGNIN) {

            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val userAccount = result.signInAccount
                val username = userAccount?.displayName
                Toast.makeText(this, getString(R.string.successful_login) + "$username", Toast.LENGTH_LONG).show()
                updateUI(1f, 0f)
            } else {
                Toast.makeText(this, getString(R.string.unable_to_login), Toast.LENGTH_LONG).show()
            }
        } else {
            if (intentActivity == getString(R.string.facebook)) {
                // Transferring the activity result back to Facebook SDK
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
            else if (intentActivity == getString(R.string.twitter)) {
                // Transferring the activity result back to Twitter SDK
                twitterAuthClient.onActivityResult(requestCode, resultCode, data)
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

