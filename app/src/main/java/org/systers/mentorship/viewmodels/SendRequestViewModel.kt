package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.utils.CommonUtils

/**
 * This class represents the [ViewModel] component used for the Send Request Activity
 */
class SendRequestViewModel : ViewModel() {

    var tag = SendRequestViewModel::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Call send a mentorship request service
     * @param relationshipRequest object containing mentorship request details
     */
    /* @SuppressLint("CheckResult")
     fun sendRequest(@NonNull relationshipRequest: RelationshipRequest) {
         relationDataManager.sendRequest(relationshipRequest)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(object : DisposableObserver<CustomResponse>() {
                     override fun onNext(customResponse: CustomResponse) {
                         message = customResponse.message ?: MentorshipApplication.getContext()
                                 .getString(R.string.registration_successful)
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
     }*/

    fun sendRequest(relationshipRequest: RelationshipRequest) {
        viewModelScope.launch {
            try {
                message = relationDataManager.sendRequest(relationshipRequest).message
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }

    }
}

