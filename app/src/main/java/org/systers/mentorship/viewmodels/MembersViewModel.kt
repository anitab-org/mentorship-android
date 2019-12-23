package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : ViewModel() {

    private val TAG = MembersViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var userList: List<User>

    /**
     * Fetches users list from getUsers method of the UserService
     */
    fun getUsers() = viewModelScope.launch {
        try {
            userList = userDataManager.getUsers()
            successful.value = true
        } catch (throwable: Exception) {
            when (throwable) {
                is IOException -> {
                    message = MentorshipApplication.getContext().getString(
                            R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    message = MentorshipApplication.getContext().getString(
                            R.string.error_request_timed_out)
                }
                is HttpException -> {
                    message = CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    message = MentorshipApplication.getContext().getString(
                            R.string.error_something_went_wrong)
                    Log.e(TAG, throwable.localizedMessage)
                }
            }
            successful.value = false
        }
    }
}
