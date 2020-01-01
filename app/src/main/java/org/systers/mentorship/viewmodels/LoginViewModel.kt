package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialRequest
import com.google.android.gms.common.api.ResolvableApiException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.MentorshipApplication.Companion.credentialsClient
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.responses.AuthToken
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.Constants.RC_REQUEST
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.activities.LoginActivity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : ViewModel() {

    var TAG = LoginViewModel::class.java.simpleName

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()
    private val context = MentorshipApplication.getContext()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    val successfulCredentials: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var username: String
    lateinit var password: String

    /**
     * Will be used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    @SuppressLint("CheckResult")
    fun login(@NonNull login: Login, saveCredentials: Boolean) {
        authDataManager.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<AuthToken>() {
                    override fun onNext(authToken: AuthToken) {
                        if (saveCredentials) {
                            val credential = Credential.Builder(login.username)
                                    .setName(login.username)
                                    .setPassword(login.password)
                                    .build()

                            credentialsClient.save(credential)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            Toast.makeText(context,
                                                    context.getString(R.string.credentials_saved_successfully),
                                                    Toast.LENGTH_LONG).show()
                                        } else {
                                            val exception = it.exception
                                            if (exception is ResolvableApiException) {
                                                try {
                                                    exception.startResolutionForResult(
                                                            LoginActivity.instance, RC_REQUEST)
                                                } catch (e: IntentSender.SendIntentException) {
                                                    Toast.makeText(context,
                                                            R.string.error_getting_credentials,
                                                            Toast.LENGTH_SHORT).show()
                                                    successfulCredentials.value = true
                                                }
                                            } else
                                                Toast.makeText(context,
                                                        context.getString(R.string.credentials_save_failed),
                                                        Toast.LENGTH_LONG).show()
                                        }
                                    }
                        }

                        successful.value = true
                        preferenceManager.putAuthToken(authToken.authToken)
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = context.getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = context.getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(throwable).message
                            }
                            else -> {
                                message = context.getString(R.string.error_something_went_wrong)
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    fun getCredentials() {
        val credentialRequest = CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build()
        credentialsClient.request(credentialRequest)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        username = it.result?.credential?.name ?: ""
                        password = it.result?.credential?.password ?: ""
                        successfulCredentials.value = true
                    } else {
                        val exception = it.exception
                        if (exception is ResolvableApiException) {
                            try {
                                exception.startResolutionForResult(LoginActivity.instance, RC_REQUEST)
                            } catch (e: IntentSender.SendIntentException) {
                                Toast.makeText(context, R.string.error_getting_credentials, Toast.LENGTH_SHORT).show()
                                successfulCredentials.value = true
                            }
                        }
                    }
                }
    }

}
