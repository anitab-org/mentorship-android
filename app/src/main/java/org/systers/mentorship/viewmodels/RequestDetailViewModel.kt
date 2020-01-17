package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
class RequestDetailViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    /**
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    fun acceptRequest(requestId: Int) = observe(relationDataManager.acceptRelationship(requestId))

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    fun rejectRequest(requestId: Int) = observe(relationDataManager.rejectRelationship(requestId))

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    fun deleteRequest(requestId: Int) = observe(relationDataManager.deleteRelationship(requestId))
}
