package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.calladapter.Simple
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class RelationDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return an Simple of a list of [Relationship]
     */
    fun getAllRelationsAndRequests(): Simple<List<Relationship>> {
        return apiManager.relationService.getAllRelationships()
    }

    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     * @return an Simple of [CustomResponse]
     */
    fun acceptRelationship(relationId: Int): Simple<CustomResponse> {
        return apiManager.relationService.acceptRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     * @return an Simple of [CustomResponse]
     */
    fun rejectRelationship(relationId: Int): Simple<CustomResponse> {
        return apiManager.relationService.rejectRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     * @return an Simple of [CustomResponse]
     */
    fun deleteRelationship(relationId: Int): Simple<CustomResponse> {
        return apiManager.relationService.deleteRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     * @return an Simple of [CustomResponse]
     */
    fun cancelRelationship(relationId: Int): Simple<CustomResponse> {
        return apiManager.relationService.cancelRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     * @return an Simple of [CustomResponse]
     */
    fun sendRequest(relationshipRequest: RelationshipRequest): Simple<CustomResponse> {
        return apiManager.relationService.sendRequest(relationshipRequest)
    }

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     * @return an Simple of [CustomResponse]
     */
    fun getCurrentRelationship(): Simple<Relationship> {
        return apiManager.relationService.getCurrentRelationship()
    }
}
