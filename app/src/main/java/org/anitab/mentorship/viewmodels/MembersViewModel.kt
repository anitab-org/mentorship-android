package org.anitab.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.datamanager.UserDataManager
import org.anitab.mentorship.remote.requests.PaginationRequest
import org.anitab.mentorship.utils.CommonUtils
import org.anitab.mentorship.utils.Constants.ITEMS_PER_PAGE

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel(
    private val userDataManager: UserDataManager
) : ViewModel() {

    var tag = MembersViewModel::class.java.simpleName

    /*private val userDataManager: UserDataManager = UserDataManager()*/

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    var currentPage = 1
    lateinit var message: String
    var userList: ArrayList<User> = arrayListOf()

    /**
     * Fetches users list from getUsers method of the UserService
     */
    fun getUsers(isRefresh: Boolean) {
        viewModelScope.launch {
            if (isRefresh) {
                userList.clear()
                currentPage = 1
            }
            try {
                userList.addAll(
                    userDataManager.getUsers(
                        PaginationRequest(
                            currentPage,
                            ITEMS_PER_PAGE
                        )
                    )
                )
                currentPage++
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    @ExperimentalPagingApi
    fun getUsersLiveData(): Flow<PagingData<User>> {
        return userDataManager.getUsers().cachedIn(viewModelScope)
    }
}
