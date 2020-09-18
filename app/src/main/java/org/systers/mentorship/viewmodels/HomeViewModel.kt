package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.repository.DbRepository
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.SingleLiveEvent
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException


/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel(val app: Application, val repo: DbRepository) : AndroidViewModel(app) {

    private val tag = this::class.java.simpleName!!
    private val userDataManager by lazy { UserDataManager() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _userStats = MutableLiveData<HomeStatistics>()
    private val _message = SingleLiveEvent<String>()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var user: User

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
                                        .also { Log.d(tag, error.localizedMessage) }
                            }
                        }
                    }

                })
                .addTo(compositeDisposable)
    }

    /**
     * Fetches the current users full profile
     */
    @SuppressLint("CheckResult")
    fun getProfile() {
        userDataManager.getUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<User>() {
                    override fun onNext(userprofile: User) {
                        user = userprofile
                        saveProfile(user)
                        successfulGet.value = true
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet))
                            }
                            is TimeoutException -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out))
                            }
                            is HttpException -> {
                                _message.postValue(CommonUtils.getErrorResponse(throwable).message)
                            }
                            else -> {
                                _message.postValue(MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong))
                                Log.e(tag, throwable.localizedMessage)
                            }
                        }
                        successfulGet.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * Saves the user's profile details
     */
    fun saveProfile(user: User) = viewModelScope.launch {
        repo.insertUser(user)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}


