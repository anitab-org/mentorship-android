package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.SingleLiveEvent

/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : ViewModel() {

    private val userDataManager by lazy { UserDataManager() }

    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val userStats: LiveData<HomeStatistics>
        get() = _userStats

    val message: LiveData<String>
        get() = _message

    init {
        userDataManager.getHomeStats()
                .process { homeStatistics, throwable ->
                    if (throwable == null)
                        _userStats.postValue(homeStatistics)
                    else {
                        throwable.printStackTrace()
                        _message.postValue(throwable.localizedMessage)
                    }
                }
    }

}

