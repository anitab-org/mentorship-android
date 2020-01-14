package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.SingleLiveEvent

/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName
    private val userDataManager by lazy { UserDataManager() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val userStats: LiveData<HomeStatistics>
        get() = _userStats

    val message: LiveData<String>
        get() = _message

    init {
        userDataManager.getHomeStats().process { statistics, throwable ->
            if (statistics != null) {
                _userStats.postValue(statistics)
            } else if (throwable != null) {
                _message.postValue(throwable.message.toString())
            }
        }
    }
}

