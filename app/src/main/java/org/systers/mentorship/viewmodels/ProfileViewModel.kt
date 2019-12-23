package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel : ViewModel() {

    private val TAG = ProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var message: String

    val user: MutableLiveData<User?> = MutableLiveData()

    init {
        getProfile()
    }

    /**
     * Fetches the current users full profile
     */
    fun getProfile() = viewModelScope.launch {
        try {
            user.value = userDataManager.getUser()
        } catch (throwable: Exception) {
            message = when (throwable) {
                is IOException -> {
                    MentorshipApplication.getContext().getString(
                            R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    MentorshipApplication.getContext().getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    Log.e(TAG, throwable.localizedMessage)
                    MentorshipApplication.getContext().getString(
                            R.string.error_something_went_wrong)
                }
            }
        }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    fun updateProfile(user: User): LiveData<Boolean> = liveData {
        try {
            message = userDataManager.updateUser(user).message
            emit(true)
        } catch (throwable: Exception) {
            message = when (throwable) {
                is IOException -> {
                    MentorshipApplication.getContext().getString(
                            R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    MentorshipApplication.getContext().getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    Log.e(TAG, throwable.localizedMessage)
                    MentorshipApplication.getContext().getString(
                            R.string.error_something_went_wrong)
                }
            }

            emit(false)
        }
    }
}
