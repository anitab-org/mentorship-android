package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.RelationshipRequest

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class RelationDataManager {
    private val apiManager = ApiManager.instance

    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return of a list of [Relationship]
     */
    suspend fun getAllRelationsAndRequests() = apiManager.relationService.getAllRelationships()

    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     */
    suspend fun acceptRelationship(relationId: Int) = apiManager.relationService.acceptRelationship(
            relationId)

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     */
    suspend fun rejectRelationship(relationId: Int) = apiManager.relationService.rejectRelationship(
            relationId)

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     */
    suspend fun deleteRelationship(relationId: Int) = apiManager.relationService.deleteRelationship(
            relationId)

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     */
    suspend fun cancelRelationship(relationId: Int) = apiManager.relationService.cancelRelationship(
            relationId)

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     */
    suspend fun sendRequest(relationshipRequest: RelationshipRequest) = apiManager.relationService.sendRequest(
            relationshipRequest)

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     */
    suspend fun getCurrentRelationship() = apiManager.relationService.getCurrentRelationship()
}
