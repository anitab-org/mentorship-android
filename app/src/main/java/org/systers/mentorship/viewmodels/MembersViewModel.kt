package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : ViewModel() {

    var tag = MembersViewModel::class.java.simpleName!!

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var userList: List<User>

    /**
     * Fetches users list from getUsers method of the UserService
     */
    @SuppressLint("CheckResult")
    fun getUsers() {
        userDataManager.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<User>>() {
                    override fun onNext(userListResponse: List<User>) {
                        userList = userListResponse
                        successful.value = true
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(throwable).message.toString()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(tag, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}

