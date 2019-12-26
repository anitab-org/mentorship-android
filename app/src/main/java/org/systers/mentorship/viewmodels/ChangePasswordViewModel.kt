package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ChangePassword

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
class ChangePasswordViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName
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
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            success = false
        }

        emit(success)
    }
}
