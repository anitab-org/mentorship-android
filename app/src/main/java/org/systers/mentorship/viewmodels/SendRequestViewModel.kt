package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Send Request Activity
 */
class SendRequestViewModel : ViewModel() {

    var TAG = SendRequestViewModel::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Call send a mentorship request service
     * @param relationshipRequest object containing mentorship request details
     */
    fun sendRequest(relationshipRequest: RelationshipRequest) = viewModelScope.launch {
        try {
            message = relationDataManager.sendRequest(relationshipRequest).message
            successful.value = true
        } catch (throwable: Throwable) {
            message = when (throwable) {
                is IOException -> {
                    MentorshipApplication.getContext().getString(
                            R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    MentorshipApplication.getContext().getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    Log.e(TAG, throwable.localizedMessage)
                    MentorshipApplication.getContext().getString(
                            R.string.error_something_went_wrong)
                }
            }
            successful.value = false
        }
    }
}
