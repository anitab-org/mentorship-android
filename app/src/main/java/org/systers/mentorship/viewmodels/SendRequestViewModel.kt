package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
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
    @SuppressLint("CheckResult")
    fun sendRequest(@NonNull relationshipRequest: RelationshipRequest) {
        relationDataManager.sendRequest(relationshipRequest).process { customResponse, throwable ->
            if (customResponse != null) {
                successful.postValue(true)
                message = customResponse.message
            } else if (throwable != null) {
                successful.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
