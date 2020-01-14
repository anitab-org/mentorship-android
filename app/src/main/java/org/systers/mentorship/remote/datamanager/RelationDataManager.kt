package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.Custom
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
     * @return an Custom of a list of [Relationship]
     */
    fun getAllRelationsAndRequests(): Custom<List<Relationship>> {
        return apiManager.relationService.getAllRelationships()
    }

    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     * @return an Custom of [CustomResponse]
     */
    fun acceptRelationship(relationId: Int): Custom<CustomResponse> {
        return apiManager.relationService.acceptRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     * @return an Custom of [CustomResponse]
     */
    fun rejectRelationship(relationId: Int): Custom<CustomResponse> {
        return apiManager.relationService.rejectRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     * @return an Custom of [CustomResponse]
     */
    fun deleteRelationship(relationId: Int): Custom<CustomResponse> {
        return apiManager.relationService.deleteRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     * @return an Custom of [CustomResponse]
     */
    fun cancelRelationship(relationId: Int): Custom<CustomResponse> {
        return apiManager.relationService.cancelRelationship(relationId)
    }

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     * @return an Custom of [CustomResponse]
     */
    fun sendRequest(relationshipRequest: RelationshipRequest): Custom<CustomResponse> {
        return apiManager.relationService.sendRequest(relationshipRequest)
    }

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     * @return an Custom of [CustomResponse]
     */
    fun getCurrentRelationship(): Custom<Relationship> {
        return apiManager.relationService.getCurrentRelationship()
    }
}
