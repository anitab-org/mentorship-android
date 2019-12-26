package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest

/**
 * This class represents the [ViewModel] component used for the Send Request Activity
 */
class SendRequestViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

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
        } catch (throwable: Exception) {
            throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }
}
