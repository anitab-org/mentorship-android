package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()
    lateinit var userProfile: User

    /**
     * Fetches profile from a user with id [userId] by calling getUser method from UserService
     * @param userId id of a member
     */
    fun getUserProfile(userId: Int) = observe(userDataManager.getUser(userId), { userProfile = it })
}
