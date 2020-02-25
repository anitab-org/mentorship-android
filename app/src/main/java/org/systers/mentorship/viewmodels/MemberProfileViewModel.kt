package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : ViewModel() {

    var tag = MemberProfileViewModel::class.java.simpleName!!

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MediatorLiveData<Boolean> = MediatorLiveData()
    lateinit var message: String
    lateinit var userProfile: User
    var userId = -1

    /**
     * Fetches profile from a user with the value of [userId] by calling getUser method from UserService
     */
    @SuppressLint("CheckResult")
    fun getUserProfile() {
        userDataManager.getUser(userId)
                .process { user, throwable ->
                    when (throwable) {
                        null -> {
                            when (user) {
                                null -> {
                                    successful.postValue(false)
                                }
                                else -> {
                                    userProfile = user
                                    successful.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }
}

