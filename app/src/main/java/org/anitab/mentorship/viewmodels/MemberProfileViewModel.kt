package org.anitab.mentorship.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel(
    private val userDataManager: UserDataManager
) : ViewModel() {

    var tag = MemberProfileViewModel::class.java.simpleName

    val successful: MediatorLiveData<Boolean> = MediatorLiveData()
    lateinit var message: String
    lateinit var userProfile: User
    var userId = -1

    /**
     * Fetches profile from a user with the value of [userId] by calling getUser method from UserService
     */
    fun getUserProfile() {
        viewModelScope.launch {
            try {
                userProfile = userDataManager.getUser(userId)
                successful.postValue(true)
            } catch (throwable: Throwable) {
                throwable.message
            }
        }
    }
}
