package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.vo.Resource
import org.systers.mentorship.vo.UserVO

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val users: LiveData<Resource<List<UserVO>>> = userDataManager.getVerifiedUsers()
}
