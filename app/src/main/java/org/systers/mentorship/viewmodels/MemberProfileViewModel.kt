package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var userProfile: User
    /**
     * Fetches profile from a user with id [userId] by calling getUser method from UserService
     * @param userId id of a member
     */
    fun getUserProfile(userId: Int) = viewModelScope.launch {
        try {
            userProfile = userDataManager.getUser(userId)
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }
}
