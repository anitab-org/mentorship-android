package org.systers.mentorship.remote.services

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.MentorshipRelationResponse
import retrofit2.http.DELETE

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
}
