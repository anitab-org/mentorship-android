package org.systers.mentorship.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.remote.responses.LoginResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.PreferenceManager
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * Created by murad on 7/26/18.
 */
class LoginViewModel : ViewModel() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    val error: MutableLiveData<String> = MutableLiveData()

    init {
        error.value = null
    }

    /**
     * Will be used to run the login method of the AuthService
     * @param loginRequest a login request object containing the credentials
     */
    fun login(@NonNull loginRequest: LoginRequest) {
        authDataManager.login(loginRequest)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableObserver<LoginResponse>() {
                    override fun onNext(loginResponse: LoginResponse) {
                        preferenceManager.putAuthToken(loginResponse.authToken)
                        error.value = null
                    }

                    override fun onError(throwable: Throwable) {

                        when (throwable) {
                            is IOException -> {
                                error.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet))
                            }
                            is TimeoutException -> {
                                error.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out))
                            }
                            is HttpException -> {
                                error.postValue(CommonUtils.getErrorResponse(throwable).message)
                            }
                            else -> {
                                error.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong))
                            }
                        }
                    }

                    override fun onComplete() {
                    }
                })
    }
}
