package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.MentorshipRelationResponse
import retrofit2.http.*

/**
 * This interface describes the methods related to Mentorship Relation REST API
 */
interface RelationService {

    /**
     * This function returns all mentorship requests and relations of the current user
     * @return an observable instance of a list of [MentorshipRelationResponse]s
     */
    @GET("mentorship_relations")
    fun getAllMentorshipRelations(): Observable<List<MentorshipRelationResponse>>

    /**
     * This function performs the acceptance of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/accept")
    fun acceptMentorshipRelation(@Path("relation_id") relationId: Int): Observable<CustomResponse>

    /**
     * This function performs the rejection of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/reject")
    fun rejectMentorshipRelation(@Path("relation_id") relationId: Int): Observable<CustomResponse>

    /**
     * This function performs the deletion of a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @DELETE("mentorship_relation/{relation_id}")
    fun deleteMentorshipRelation(@Path("relation_id") relationId: Int): Observable<CustomResponse>

    /**
     * This function performs the cancellation of a mentorship relation
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/cancel")
    fun cancelMentorshipRelation(@Path("relation_id") relationId: Int): Observable<CustomResponse>

    /**
     * This function performs sending a mentorship request
     * @param relationshipRequest data required to send a mentorship request
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/send_request")
    fun sendMentorshipRequest(@Body relationshipRequest: RelationshipRequest): Observable<CustomResponse>

    /**
     * This function return the current mentorship relation
     * @return an observable instance of [MentorshipRelationResponse]
     */
    @GET("mentorship_relations/current")
    fun getCurrentMentorshipRelation(): Observable<MentorshipRelationResponse>
}
