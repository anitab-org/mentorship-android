package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var user: User

    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Fetches the current users full profile
     */
    fun getProfile() = observe(userDataManager.getUser(), { user = it })

    /**
     * Updates the current user profile with data changed by the user
     */
    fun updateProfile(user: User) = observe(
        userDataManager.updateUser(user),
        success = { successfulUpdate.value = true },
        failure = { successfulUpdate.value = false },
        changeDefaultStatus = false
    )
}
