package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ForgotPassword
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class ForgotPasswordViewModel {

    private val authDataManager: AuthDataManager = AuthDataManager()
    val success: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String


    @SuppressLint("CheckResult")
    fun forgotPassword(forgotPassword: ForgotPassword) {
        authDataManager.forgotPassword(forgotPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(customResponse: CustomResponse) {
                        message = customResponse.message
                        success.value = true
                    }

                    override fun onError(e: Throwable) {
                        when (e) {
                            is IOException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(e).message
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                            }
                        }
                        success.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}