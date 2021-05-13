package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.SingleLiveEvent
import retrofit2.HttpException

/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : ViewModel() {

    private val tag = this::class.java.simpleName!!
    private val userDataManager by lazy { UserDataManager() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val userStats: LiveData<HomeStatistics>
        get() = _userStats

    val message: LiveData<String>
        get() = _message

    /**
     * Fetches home stats from getHomeStats method of the UserService
     */
    fun getHomeStats() {
        userDataManager.getHomeStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<HomeStatistics>() {

                    override fun onComplete() {
                        // Do nothing
                    }

                    override fun onNext(statistics: HomeStatistics) {
                        _userStats.value = statistics
                    }

                    override fun onError(error: Throwable) {
                        when (error) {
                            is IOException -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet))
                            }
                            is TimeoutException -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out))
                            }
                            is HttpException -> {
                                _message.postValue(CommonUtils.getErrorResponse(error).message)
                            }
                            else -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong))
                                        .also { Log.d(tag, error.localizedMessage) }
                            }
                        }
                    }
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
