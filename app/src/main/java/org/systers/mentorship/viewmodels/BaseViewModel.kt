package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.SingleLiveEvent
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

abstract class BaseViewModel : ViewModel() {

    abstract val TAG: String
    val successful: MutableLiveData<Boolean> = MutableLiveData()
    val _message = SingleLiveEvent<String>()
    val liveMessage: LiveData<String>
        get() = _message
    lateinit var message: String

    fun observer(next: (t: Any) -> Unit, error: (() -> Unit)?): DisposableObserver<Any> {
        return object: DisposableObserver<Any>() {

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
                        message = CommonUtils.getErrorResponse(throwable).message.toString()
                    }
                    else -> {
                        message = MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                        Log.e(TAG, throwable.localizedMessage)
                    }
                }
                _message.value = message
                Log.i("TAG", "error msg in baseviewmodel: "+ liveMessage.value)
                successful.value = false
                error?.let { it() }
            }

            override fun onComplete() {
                // Do nothing
            }

            override fun onNext(nextItem: Any) {
                next(nextItem)
                successful.value = true
            }
        }
    }
}
