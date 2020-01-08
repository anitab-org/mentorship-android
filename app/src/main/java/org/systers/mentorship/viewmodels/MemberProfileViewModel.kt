package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.vo.Resource
import org.systers.mentorship.vo.UserVO

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    /**
     * Fetches profile from a user with id [userId] by calling getUser method from UserService
     */
    fun getUserProfile(userId: Int): LiveData<Resource<UserVO>> = userDataManager.getUser(userId)
}
