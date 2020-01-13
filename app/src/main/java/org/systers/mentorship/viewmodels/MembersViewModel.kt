package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

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
        userDataManager.getUsers().process { users, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (users != null) {
                    userList = users
                    successful.postValue(true)
                } else {
                    message = "No users"
                    successful.postValue(false)
                }
            }
        }
    }
}
