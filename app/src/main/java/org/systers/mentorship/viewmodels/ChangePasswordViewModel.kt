package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
class ChangePasswordViewModel : ViewModel() {

    private val userDataManager: UserDataManager = UserDataManager()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Updates the password of the current user
     */
    /* @SuppressLint("CheckResult")
     fun changeUserPassword(changePassword: ChangePassword) {
         userDataManager.updatePassword(changePassword)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(object : DisposableObserver<CustomResponse>() {
                     override fun onNext(customResponse: CustomResponse) {
                         message = customResponse.message
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
     }*/
    fun changeUserPassword(changePassword: ChangePassword) {
        viewModelScope.launch {
            try {
                userDataManager.updatePassword(changePassword)
                successfulUpdate.postValue(true)
            } catch (throwable: Throwable) {
                message = when (throwable) {
                    is IOException -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_please_check_internet)
                    }
                    is TimeoutException -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_request_timed_out)
                    }
                    is HttpException -> {
                        CommonUtils.getErrorResponse(throwable).message
                    }
                    else -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                    }
                }
                successfulUpdate.postValue(false)
            }
        }

    }
}
