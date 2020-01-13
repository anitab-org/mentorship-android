package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest

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
    fun sendRequest(relationshipRequest: RelationshipRequest) {
        relationDataManager.sendRequest(relationshipRequest).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (customResponse != null) {
                    customResponse.message
                    successful.postValue(true)
                } else {
                    successful.postValue(false)
                }
            }
        }
    }
}
