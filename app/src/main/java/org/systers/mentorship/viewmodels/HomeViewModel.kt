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
import org.systers.mentorship.vo.Resource


/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : ViewModel() {
    private val TAG = this::class.java.simpleName

    private val userDataManager by lazy { UserDataManager() }

    val homeStats: LiveData<Resource<HomeStatistics>> = userDataManager.getHomeStats()
}


