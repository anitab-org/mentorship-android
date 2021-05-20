package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
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
import org.systers.mentorship.remote.requests.PaginationRequest
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.Constants.ITEMS_PER_PAGE
import retrofit2.HttpException

/**
 * This class represents the [ViewModel] component used for the Members Activity
 */
class MembersViewModel : ViewModel() {

    var tag = MembersViewModel::class.java.simpleName!!

    private val userDataManager: UserDataManager = UserDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    var currentPage = 1
    lateinit var message: String
    var userList: ArrayList<User> = arrayListOf()

    /**
     * Fetches users list from getUsers method of the UserService
     */
    @SuppressLint("CheckResult")
    fun getUsers(isRefresh: Boolean) {
        if (isRefresh) {
            userList.clear()
            currentPage = 1
        }
        userDataManager.getUsers(paginationRequest = PaginationRequest(currentPage, ITEMS_PER_PAGE))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<User>>() {
                    override fun onNext(userListResponse: List<User>) {
                        userList.addAll(userListResponse)
                        successful.value = true
                        currentPage++
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
