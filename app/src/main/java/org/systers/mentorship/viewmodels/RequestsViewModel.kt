package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : ViewModel() {

    private val TAG = RequestsViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MediatorLiveData<Boolean> = MediatorLiveData()
    private val allSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    private val pastSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    var message: String? = null
    lateinit var allRequestsList: List<Relationship>
    lateinit var pastRequestsList: List<Relationship>

    // Combine data coming from two LiveData objects
    init {
        successful.addSource(allSuccessful) { allSuccessful ->
            successful.value = allSuccessful && (pastSuccessful.value ?: false)
        }

        successful.addSource(pastSuccessful) { pastSuccessful ->
            successful.value = pastSuccessful && (allSuccessful.value ?: false)
        }
    }

    /**
     * Fetches list of all Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getAllMentorshipRelations() {
        relationDataManager.getAllRelationsAndRequests()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Relationship>>() {
                    override fun onNext(relationsList: List<Relationship>) {
                        allRequestsList = relationsList
                        allSuccessful.value = true
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
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        allSuccessful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * Fetches list of *past* Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getPastMentorshipRelations() {
        relationDataManager.getPastRelationsAndRequests()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Relationship>>() {
                    override fun onNext(relationsList: List<Relationship>) {
                        pastRequestsList = relationsList
                        pastSuccessful.value = true
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
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        pastSuccessful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}
