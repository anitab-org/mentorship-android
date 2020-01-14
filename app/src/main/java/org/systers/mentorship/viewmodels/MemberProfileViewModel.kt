package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
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
    @SuppressLint("CheckResult")
    fun getUserProfile(@NonNull userId: Int) {
        userDataManager.getUser(userId).process { user, throwable ->
            if (user != null) {
                successful.postValue(true)
                userProfile = user
            }  else if (throwable != null) {
                successful.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
