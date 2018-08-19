package org.systers.mentorship.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.responses.MentorshipRelationResponse
import org.systers.mentorship.utils.CommonUtils

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : ViewModel() {

    var TAG = RequestsViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var allRequestsList: List<MentorshipRelationResponse>

    /**
     * Fetches list of all Mentorship relations and requests
     */
    fun getAllMentorshipRelations() {
        relationDataManager.getAllMentorshipRelationsAndRequests()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<MentorshipRelationResponse>>() {
                    override fun onNext(relationsList: List<MentorshipRelationResponse>) {
                        allRequestsList = relationsList
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
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}