package org.systers.mentorship.remote.services

import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.Custom
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.*

/**
 * This interface describes the methods related to Mentorship Relation REST API
 */
interface RelationService {

    /**
     * This function returns all mentorship requests and relations of the current user
     * @return an Custom instance of a list of [Relationship]s
     */
    @GET("mentorship_relations")
    fun getAllRelationships(): Custom<List<Relationship>>

    /**
     * This function performs the acceptance of a mentorship request
     * @return an Custom instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/accept")
    fun acceptRelationship(@Path("relation_id") relationId: Int): Custom<CustomResponse>

    /**
     * This function performs the rejection of a mentorship request
     * @return an Custom instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/reject")
    fun rejectRelationship(@Path("relation_id") relationId: Int): Custom<CustomResponse>

    /**
     * This function performs the deletion of a mentorship request
     * @return an Custom instance of [CustomResponse] with a proper error or success message
     */
    @DELETE("mentorship_relation/{relation_id}")
    fun deleteRelationship(@Path("relation_id") relationId: Int): Custom<CustomResponse>

    /**
     * This function performs the cancellation of a mentorship relation
     * @return an Custom instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/cancel")
    fun cancelRelationship(@Path("relation_id") relationId: Int): Custom<CustomResponse>

    /**
     * This function performs sending a mentorship request
     * @param relationshipRequest data required to send a mentorship request
     * @return an Custom instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/send_request")
    fun sendRequest(@Body relationshipRequest: RelationshipRequest): Custom<CustomResponse>

    /**
     * This function return the current mentorship relation
     * @return an Custom instance of [Relationship]
     */
    @GET("mentorship_relations/current")
    fun getCurrentRelationship(): Custom<Relationship>
}
