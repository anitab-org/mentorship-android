package org.anitab.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel(userDataManager: UserDataManager) : ViewModel() {

    private val userListNoFilter: LiveData<PagingData<User>> =
        userDataManager.getAllUsers().cachedIn(viewModelScope)

    private val userLisFilterByNeedMentoring: LiveData<PagingData<User>> =
        userDataManager.getUsersWhoAreNeedMentoring().cachedIn(viewModelScope)

    private val userListFilterByAvailableToMentor: LiveData<PagingData<User>> =
        userDataManager.getUsersWhoAreAvailableToMentor().cachedIn(viewModelScope)

    private val userListFilterByHaveSkill: LiveData<PagingData<User>> =
        userDataManager.getUserWhoHaveSkills().cachedIn(viewModelScope)

    val userList = MediatorLiveData<PagingData<User>>()

    // setting default filter sa no filter
    private var selectedUserFilter = ListFilter.NO_FILTER

    init {
        userList.addSource(userListNoFilter) { list ->
            if (selectedUserFilter == ListFilter.NO_FILTER) {
                list?.let { userList.value = it }
            }
        }

        userList.addSource(userLisFilterByNeedMentoring) { list ->
            if (selectedUserFilter == ListFilter.NEED_MENTORING) {
                list?.let { userList.value = it }
            }
        }

        userList.addSource(userListFilterByAvailableToMentor) { list ->
            if (selectedUserFilter == ListFilter.AVAILABLE_TO_MENTOR) {
                list?.let { userList.value = it }
            }
        }

        userList.addSource(userListFilterByHaveSkill) { list ->
            if (selectedUserFilter == ListFilter.HAVE_SKILL) {
                list?.let { userList.value = it }
            }
        }
    }

    fun getFilteredUserList(filter: ListFilter) = when (filter) {
        ListFilter.NO_FILTER -> userListNoFilter.value?.let { userList.value = it }
        ListFilter.NEED_MENTORING -> userLisFilterByNeedMentoring.value?.let { userList.value = it }
        ListFilter.AVAILABLE_TO_MENTOR -> userListFilterByAvailableToMentor.value?.let { userList.value = it }
        ListFilter.HAVE_SKILL -> userListFilterByHaveSkill.value?.let { userList.value = it }
    }.also { selectedUserFilter = filter }
}

enum class ListFilter {
    NO_FILTER, NEED_MENTORING, AVAILABLE_TO_MENTOR, HAVE_SKILL
}
