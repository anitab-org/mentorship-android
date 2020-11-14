package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.Email
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.CountingIdlingResourceSingleton
import org.systers.mentorship.viewmodels.LoginViewModel
import retrofit2.HttpException
import java.io.IOException


/**
 * This activity will let the user to login using username/email and password.
 */
class LoginActivity : BaseActivity() {

    private val loginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var message: String
    private val userDataManager by lazy { UserDataManager() }
    var tag = LoginActivity::class.java.simpleName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
                    if(loginViewModel.verified)
                        Snackbar.make(getRootView(), loginViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                    else
                        Snackbar.make(getRootView(), R.string.not_verified, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.resend_email) {
                                    val data = Email(username)
                                            userDataManager.resendEmail(data)
                                            .subscribeOn(Schedulers.newThread())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(object: DisposableObserver<CustomResponse>() {
                                                override fun onNext(customResponse: CustomResponse) {
                                                    message = customResponse.message
                                                    Snackbar.make(getRootView(), message, Snackbar.LENGTH_SHORT).show()
                                                }

                                                override fun onError(throwable: Throwable) {
                                                    when (throwable) {
                                                        is IOException -> {
                                                            message = R.string.error_please_check_internet.toString();
                                                            Log.e(tag, throwable.localizedMessage)
                                                        }
                                                        is HttpException -> {
                                                            message = CommonUtils.getErrorResponse(throwable).message.toString()
                                                        }
                                                        else -> {
                                                            message = R.string.error_something_went_wrong.toString();
                                                            Log.e(tag, throwable.localizedMessage)
                                                        }
                                                    }
                                                    Log.d(tag, message, throwable)
                                                    Snackbar.make(getRootView(), message, Snackbar.LENGTH_LONG).show()
                                                }

                                                override fun onComplete() {
                                                    Snackbar.make(getRootView(),  R.string.sent_email, Snackbar.LENGTH_LONG).show()
                                                }
                                            })
                                }
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


