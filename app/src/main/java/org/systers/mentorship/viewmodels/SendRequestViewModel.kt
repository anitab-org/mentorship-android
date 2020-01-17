package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest

/**
 * This class represents the [ViewModel] component used for the Send Request Activity
 */
class SendRequestViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    /**
     * Call send a mentorship request service
     * @param relationshipRequest object containing mentorship request details
     */
    fun sendRequest(relationshipRequest: RelationshipRequest) =
        observe(relationDataManager.sendRequest(relationshipRequest))
}
