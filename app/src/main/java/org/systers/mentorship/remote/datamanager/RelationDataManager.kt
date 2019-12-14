package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class RelationDataManager {

    private val relationService = ApiManager.instance.relationService

    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return an Observable of a list of [Relationship]
     */
    fun getAllRelationsAndRequests() = relationService.getAllRelationships()

    /**
     * This will call a method of RelationService interface to fetch
     * all pending mentorship requests and relations
     * @return an Observable of a list of [Relationship]
     */
    fun getPendingRelationsAndRequests() = relationService.getPendingRelationships()


    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     * @return an Observable of [CustomResponse]
     */
    fun acceptRelationship(relationId: Int) = relationService.acceptRelationship(relationId)

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     * @return an Observable of [CustomResponse]
     */
    fun rejectRelationship(relationId: Int) = relationService.rejectRelationship(relationId)

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     * @return an Observable of [CustomResponse]
     */
    fun deleteRelationship(relationId: Int) = relationService.deleteRelationship(relationId)

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     * @return an Observable of [CustomResponse]
     */
    fun cancelRelationship(relationId: Int) = relationService.cancelRelationship(relationId)

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     * @return an Observable of [CustomResponse]
     */
    fun sendRequest(relationshipRequest: RelationshipRequest) = relationService.sendRequest(relationshipRequest)

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     * @return an Observable of [CustomResponse]
     */
    fun getCurrentRelationship() = relationService.getCurrentRelationship()
}
