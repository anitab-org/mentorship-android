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
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.PreferenceManager
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for ProfileFragment
 */
class ProfileViewModel: ViewModel() {

    var tag = ProfileViewModel::class.java.simpleName!!

    private val userDataManager: UserDataManager = UserDataManager()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var user: User
    lateinit var message: String

    private val sharedPreference : PreferenceManager = PreferenceManager()

    /**
     * Fetches the current users full profile
     */
    @SuppressLint("CheckResult")
    fun getProfile() {
        //fetching the value from shared preferences
        val userProfile = sharedPreference.getProfileDetails()
        if (userProfile !== null) {
            user = userProfile
            successfulGet.value = true
        } else {
            //fetching users full profile via API call
            userDataManager.getUser()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<User>() {
                        override fun onNext(userprofile: User) {
                            //putting the value in shared preferences
                            sharedPreference.putProfileDetails(userprofile)
                            user = userprofile
                            successfulGet.value = true
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
                            successfulGet.value = false
                        }

                        override fun onComplete() {
                        }
                    })
        }
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    @SuppressLint("CheckResult")
    fun updateProfile(user: User) {

        //updating user's data via API call
        userDataManager.updateUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(response: CustomResponse) {
                        //updating data in sharedPreference
                        sharedPreference.putProfileDetails(user)
                        successfulUpdate.value = true
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
                        successfulUpdate.value = false
                    }
                    override fun onComplete() {
                    }
                })
    }
}

