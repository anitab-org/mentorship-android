package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
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
    lateinit var message: String

    /**
     * Updates the password of the current user
     */
    fun changeUserPassword(changePassword: ChangePassword): LiveData<Boolean> = liveData {
        var success: Boolean
        try {
            val response = userDataManager.updatePassword(changePassword)
            message = response.message
            success = true
        } catch (throwable: Exception) {
            message = when (throwable) {
                is IOException -> {
                    MentorshipApplication.getContext().getString(R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    MentorshipApplication.getContext().getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    MentorshipApplication.getContext().getString(R.string.error_something_went_wrong)
                }
            }
            success = false
        }

        emit(success)
    }
}
