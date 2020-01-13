package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel : ViewModel() {

    var TAG = ProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var user: User
    lateinit var message: String

    /**
     * Fetches the current users full profile
     */
    fun getProfile() {
        userDataManager.getUser().process { user, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successfulGet.postValue(false)
            } else {
                if (user != null) {
                    successfulGet.postValue(true)
                    this.user = user
                } else {
                    successfulGet.postValue(false)
                }
            }
        }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    fun updateProfile(user: User) {
        userDataManager.updateUser(user).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successfulUpdate.postValue(false)
            } else {
                if (customResponse != null) {
                    successfulUpdate.postValue(true)
                } else {
                    successfulUpdate.postValue(false)
                }
            }
        }
    }
}
