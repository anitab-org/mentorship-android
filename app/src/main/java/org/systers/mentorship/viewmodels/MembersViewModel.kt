package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : ViewModel() {

    var TAG = MembersViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var userList: List<User>

    /**
     * Fetches users list from getUsers method of the UserService
     */
    @SuppressLint("CheckResult")
    fun getUsers() {
        userDataManager.getUsers()
                .process { list, throwable ->
                    when(throwable){
                        null ->{
                            when(list){
                                null ->{
                                    successful.postValue(false)
                                }
                                else ->{
                                    successful.postValue(true)
                                    userList = list
                                }
                            }
                        }
                        else ->{
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }
}
