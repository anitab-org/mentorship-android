package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.SingleLiveEvent


/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : ViewModel() {
    private val TAG = this::class.java.simpleName

    private val userDataManager by lazy { UserDataManager() }
    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val userStats: LiveData<HomeStatistics>
        get() = _userStats

    val message: LiveData<String>
        get() = _message

    init {
        getHomeStats()
    }

    private fun getHomeStats() = viewModelScope.launch {
        try {
            _userStats.value = userDataManager.getHomeStats()
        } catch (throwable: Exception) {
            _message.value = throwable.handleNetworkExceptionWithMessage(TAG)
        }
    }
}


