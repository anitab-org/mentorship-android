package org.systers.mentorship.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
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
    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val userStats: LiveData<HomeStatistics>
        get() = _userStats

    val message: LiveData<String>
        get() = _message

    init {
        userDataManager.getHomeStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<HomeStatistics>() {

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
                                        .also { Log.d(TAG, error.localizedMessage) }
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

