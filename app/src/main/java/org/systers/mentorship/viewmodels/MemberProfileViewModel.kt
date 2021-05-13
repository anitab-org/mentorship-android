package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : ViewModel() {

    var tag = MemberProfileViewModel::class.java.simpleName!!

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MediatorLiveData<Boolean> = MediatorLiveData()
    lateinit var message: String
    lateinit var userProfile: User
    var userId = -1

    /**
     * Fetches profile from a user with the value of [userId] by calling getUser method from UserService
     */
    @SuppressLint("CheckResult")
    fun getUserProfile() {
        userDataManager.getUser(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<User>() {
                    override fun onNext(user: User) {
                        userProfile = user
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
                                message = CommonUtils.getErrorResponse(throwable).message
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
