package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.SingleLiveEvent
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException


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
            when (throwable) {
                is IOException -> {
                    _message.postValue(MentorshipApplication.getContext().getString(
                            R.string.error_please_check_internet))
                }
                is TimeoutException -> {
                    _message.postValue(MentorshipApplication.getContext().getString(
                            R.string.error_request_timed_out))
                }
                is HttpException -> {
                    _message.postValue(CommonUtils.getErrorResponse(throwable).message)
                }
                else -> {
                    _message.postValue(MentorshipApplication.getContext().getString(
                            R.string.error_something_went_wrong)).also {
                        Log.d(TAG, throwable.localizedMessage)
                    }
                }
            }
        }
    }
}

