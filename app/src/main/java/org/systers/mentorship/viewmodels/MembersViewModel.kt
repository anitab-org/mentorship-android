package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : BaseViewModel() {

    override var TAG = MembersViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var userList: List<User>

    /**
     * Fetches users list from getUsers method of the UserService
     */
    @SuppressLint("CheckResult")
    fun getUsers() {
        userDataManager.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    // we can't simply cast Any to List
                    if (it is List<*>)
                        userList = it.filterIsInstance<User>()
                })
    }
}
