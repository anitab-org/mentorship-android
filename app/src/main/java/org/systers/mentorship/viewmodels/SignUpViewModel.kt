package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.content.IntentSender
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.common.api.ResolvableApiException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.MentorshipApplication.Companion.credentialsClient
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.SignUpActivity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : ViewModel() {

    var TAG = SignUpViewModel::class.java.simpleName

    private val authDataManager: AuthDataManager = AuthDataManager()
    private val context = MentorshipApplication.getContext()
    val successful: MutableLiveData<Boolean> = MutableLiveData()
    /*
        We need successfulCredentials in order to know, when to navigate
         to LoginActivity, as we want to save the credentials, then navigate
         to LoginActivity and then use them. If we weren't waiting for the
         save credentials dialog to close, there would be 2 dialogs in
         LoginActivity, so that one of them would be closed, which means
         that user wouldn't be able to save credentials.
     */
    val successfulCredentials: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Will be used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
    @SuppressLint("CheckResult")
    fun register(@NonNull register: Register) {
        authDataManager.register(register)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(customResponse: CustomResponse) {
                        val credential = Credential.Builder(register.email)
                                .setName(register.username)
                                .setPassword(register.password)
                                .build()

                        credentialsClient.save(credential)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(context,
                                                context.getString(R.string.credentials_saved_successfully),
                                                Toast.LENGTH_LONG).show()
                                        successfulCredentials.value = true
                                    } else {
                                        val exception = it.exception
                                        if (exception is ResolvableApiException) {
                                            try {
                                                exception.startResolutionForResult(
                                                        SignUpActivity.instance, Constants.RC_REQUEST)
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
                                        successfulCredentials.value = false
                                    }
                                }

                        message = customResponse.message
                        successful.value = true
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
}
