package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
class RequestDetailViewModel : ViewModel() {

    var TAG = RequestDetailViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    fun acceptRequest(requestId: Int) {
        relationDataManager.acceptRelationship(requestId).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (customResponse != null) {
                    message = customResponse.message
                    successful.postValue(true)
                } else {
                    successful.postValue(false)
                }
            }
        }
    }

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    fun rejectRequest(requestId: Int) {
        relationDataManager.rejectRelationship(requestId).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (customResponse != null) {
                    message = customResponse.message
                    successful.postValue(true)
                } else {
                    successful.postValue(false)
                }
            }
        }
    }

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    fun deleteRequest(requestId: Int) {
        relationDataManager.deleteRelationship(requestId).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (customResponse != null) {
                    message = customResponse.message
                    successful.postValue(true)
                } else {
                    successful.postValue(false)
                }
            }
        }
    }
}
