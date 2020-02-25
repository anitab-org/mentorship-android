package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ChangePassword

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
class ChangePasswordViewModel : ViewModel() {

    private val userDataManager: UserDataManager = UserDataManager()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Updates the password of the current user
     */
    @SuppressLint("CheckResult")
    fun changeUserPassword(changePassword: ChangePassword) {
        userDataManager.updatePassword(changePassword)
                .process { customResponse, throwable ->
                    when(throwable){
                        null ->{
                            when(customResponse?.message){
                                null ->{
                                    successfulUpdate.postValue(false)
                                }
                                else ->{
                                    successfulUpdate.postValue(true)
                                    message = customResponse.message
                                }
                            }
                        }
                        else ->{
                            message = throwable.localizedMessage
                            successfulUpdate.postValue(false)
                        }
                    }

                }
    }
}
