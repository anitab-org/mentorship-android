package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : ViewModel() {

    var TAG = SignUpViewModel::class.java.simpleName

    private val authDataManager: AuthDataManager = AuthDataManager()
    private val appContext = MentorshipApplication.getContext()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    fun register(register: Register) = viewModelScope.launch {
        try {
            val response = authDataManager.register(register)
            message = response.message
            successful.value = true
        } catch (throwable: Exception) {
            when (throwable) {
                is IOException -> {
                    message = appContext.getString(R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    message = appContext.getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    message = CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    message = appContext.getString(R.string.error_something_went_wrong)
                    Log.e(TAG, throwable.localizedMessage)
                }
            }
            successful.value = false
        }
    }

    /**
     * Used used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
//    @SuppressLint("CheckResult")
//    fun register(@NonNull register: Register) {
//        authDataManager.register(register)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableObserver<CustomResponse>() {
//                    override fun onNext(customResponse: CustomResponse) {
//                        message = customResponse.message
//                        successful.value = true
//                    }
//
//                    override fun onError(throwable: Throwable) {
//                        when (throwable) {
//                            is IOException -> {
//                                message = MentorshipApplication.getContext()
//                                        .getString(R.string.error_please_check_internet)
//                            }
//                            is TimeoutException -> {
//                                message = MentorshipApplication.getContext()
//                                        .getString(R.string.error_request_timed_out)
//                            }
//                            is HttpException -> {
//                                message = CommonUtils.getErrorResponse(throwable).message
//                            }
//                            else -> {
//                                message = MentorshipApplication.getContext()
//                                        .getString(R.string.error_something_went_wrong)
//                                Log.e(TAG, throwable.localizedMessage)
//                            }
//                        }
//                        successful.value = false
//                    }
//
//                    override fun onComplete() {
//                    }
//                })
//    }
}
