package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ResetPassword
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for ResetPasswordFragment
 */
class ResetPasswordViewModel : ViewModel() {

    private val userDataManager: UserDataManager = UserDataManager()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * update the password of the current user
     */
    @SuppressLint("CheckResult")
    fun changePassword(resetPassword: ResetPassword) {
        userDataManager.updatePassword(resetPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(t: CustomResponse) {
                        message = t.message
                        successfulUpdate.value = true
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
                        successfulUpdate.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}