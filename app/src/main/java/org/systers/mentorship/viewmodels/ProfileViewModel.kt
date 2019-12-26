package org.systers.mentorship.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var message: String

    val user: MutableLiveData<User?> = MutableLiveData()

    init {
        getProfile()
    }

    /**
     * Fetches the current users full profile
     */
    fun getProfile() = viewModelScope.launch {
        try {
            user.value = userDataManager.getUser()
        } catch (throwable: Exception) {
            throwable.handleNetworkExceptionWithMessage(TAG)
        }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    fun updateProfile(user: User): LiveData<Boolean> = liveData {
        try {
            message = userDataManager.updateUser(user).message
            emit(true)
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)

            emit(false)
        }
    }
}
