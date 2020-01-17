package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
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
    fun getUsers() = observe(userDataManager.getUsers(), { userList = it })
}
