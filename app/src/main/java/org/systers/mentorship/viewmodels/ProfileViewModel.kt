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
        userDataManager.getUser()
                .process { user, throwable ->
                    when(throwable){
                        null ->{
                            when(user){
                                null ->{
                                    successfulGet.postValue(false)
                                }
                                else ->{
                                    successfulGet.postValue(true)
                                    this.user = user
                                }
                            }
                        }
                        else ->{
                            message = throwable.localizedMessage
                            successfulGet.postValue(false)
                        }
                    }
                }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    @SuppressLint("CheckResult")
    fun updateProfile(user: User) {
        userDataManager.updateUser(user)
                .process { _, throwable ->
                    when(throwable){
                        null ->{
                            successfulUpdate.postValue(true)
                        }
                        else ->{
                            message = throwable.localizedMessage
                            successfulUpdate.postValue(false)
                        }
                    }
                }
    }

}
