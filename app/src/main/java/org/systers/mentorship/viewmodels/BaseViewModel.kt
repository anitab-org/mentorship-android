package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

abstract class BaseViewModel : ViewModel() {

    abstract val TAG: String

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Observes specified [observable]. Subscription hapens on a new thread.
     * @param [success] optional method to be called when [observable] subscriptions ends with a success
     * @param [failure] optional method to be called when [observable] subscription ends with a failure
     * @param [changeDefaultStatus] When set to `false`, value of [successful] **will not** be
     * changed automatically by this method.
     * ### When should I set [changeDefaultStatus] to `false`?
     * It should be used when the inheriting ViewModel class has multiple "status LiveData",
     * like *successfulUpdate* or *successfulCancel*.*
     * In other words, set this to `false` when the inheriting ViewModel defines its own MutableLiveData<Boolean>
     * serving as operation result status holders.
     */
    @SuppressLint("CheckResult")
    inline fun <T> observe(
        observable: Observable<T>,
        crossinline success: (T) -> Unit = {}, // It is non nullable, because inlined functions cannot be nullable
        crossinline failure: (Throwable) -> Unit = {},
        changeDefaultStatus: Boolean = true
    ) {
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onNext(response: T) {
                    if (response is CustomResponse) {
                        message = response.message
                    }
                    success(response)
                    if (changeDefaultStatus) {
                        successful.value = true
                    }
                }

                override fun onError(throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_please_check_internet)
                        }
                        is TimeoutException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_request_timed_out)
                        }
                        is HttpException -> {
                            message = CommonUtils.getErrorResponse(throwable).message
                        }
                        else -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                            Log.e(TAG, throwable.localizedMessage)
                        }
                    }
                    failure(throwable)
                    if (changeDefaultStatus) {
                        successful.value = false
                    }
                }

                override fun onComplete() {
                }
            })
    }
}
