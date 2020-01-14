package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel: ViewModel() {

    var TAG = ProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var user: User
    lateinit var message: String

    /**
     * Fetches the current users full profile
     */
    @SuppressLint("CheckResult")
    fun getProfile() {
        userDataManager.getUser().process { userprofile, throwable ->
            if (userprofile != null) {
                successfulGet.postValue(true)
                user = userprofile
            }  else if (throwable != null) {
                successfulGet.postValue(false)
                message = throwable.message.toString()
            }
        }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    @SuppressLint("CheckResult")
    fun updateProfile(user: User) {
        userDataManager.updateUser(user).process { response, throwable ->
            if (response != null) {
                successfulUpdate.postValue(true)
            }else if (throwable != null) {
                successfulUpdate.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
