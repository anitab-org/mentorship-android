package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

abstract class BaseViewModel : ViewModel() {

    abstract val TAG: String
    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    fun observer(error: (() -> Unit)? = null, next: (t: Any) -> Unit) = object : DisposableObserver<Any>() {

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
            successful.value = false
            error?.let { it() }
        }

        override fun onComplete() {}

        override fun onNext(t: Any) {
            next(t)
            successful.value = true
        }

    }

}