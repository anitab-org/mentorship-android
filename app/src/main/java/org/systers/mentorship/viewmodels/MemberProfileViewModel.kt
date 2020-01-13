package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : ViewModel() {

    var TAG = MemberProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var userProfile: User

    /**
     * Fetches profile from a user with id [userId] by calling getUser method from UserService
     * @param userId id of a member
     */
    fun getUserProfile(userId: Int) {
        userDataManager.getUser(userId).process { user, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (user != null) {
                    successful.postValue(true)
                    userProfile = user
                } else {
                    successful.postValue(false)
                }
            }
        }
    }
}
