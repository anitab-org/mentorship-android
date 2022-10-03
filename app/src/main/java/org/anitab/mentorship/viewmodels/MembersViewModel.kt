package org.anitab.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel(userDataManager: UserDataManager) : ViewModel() {

    private var _userListLiveData = userDataManager.getUsers().cachedIn(viewModelScope)
    val userListLiveData: Flow<PagingData<User>> = _userListLiveData

}
