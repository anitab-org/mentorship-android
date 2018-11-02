package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.models.Relationship

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class RelationDataManager {

    private val apiManager: ApiManager = ApiManager()

    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return an Observable of a list of [Relationship]
     */
    fun getAllMentorshipRelationsAndRequests(): Observable<List<Relationship>> {
        return apiManager.getMentorshipRelationService().getAllMentorshipRelations()
    }

    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     * @return an Observable of [CustomResponse]
     */
    fun acceptMentorshipRelation(relationId: Int): Observable<CustomResponse> {
        return apiManager.getMentorshipRelationService().acceptMentorshipRelation(relationId)
    }

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     * @return an Observable of [CustomResponse]
     */
    fun rejectMentorshipRelation(relationId: Int): Observable<CustomResponse> {
        return apiManager.getMentorshipRelationService().rejectMentorshipRelation(relationId)
    }

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     * @return an Observable of [CustomResponse]
     */
    fun deleteMentorshipRelation(relationId: Int): Observable<CustomResponse> {
        return apiManager.getMentorshipRelationService().deleteMentorshipRelation(relationId)
    }

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     * @return an Observable of [CustomResponse]
     */
    fun cancelMentorshipRelation(relationId: Int): Observable<CustomResponse> {
        return apiManager.getMentorshipRelationService().cancelMentorshipRelation(relationId)
    }

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     * @return an Observable of [CustomResponse]
     */
    fun sendMentorshipRequest(relationshipRequest: RelationshipRequest): Observable<CustomResponse> {
        return apiManager.getMentorshipRelationService().sendMentorshipRequest(relationshipRequest)
    }

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     * @return an Observable of [CustomResponse]
     */
    fun getCurrentMentorshipRelation(): Observable<Relationship> {
        return apiManager.getMentorshipRelationService().getCurrentMentorshipRelation()
    }
}
