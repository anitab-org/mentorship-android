package org.systers.mentorship.remote.datamanager

import androidx.lifecycle.LiveData
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.NetworkBoundResource
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.vo.Resource

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class RelationDataManager {
    private val apiManager = ApiManager.instance
    private val context = MentorshipApplication.getContext()
    private val relationshipDao = AppDatabase.getInstance(context).relationshipDao()

    /**
     * This will call a method of RelationService interface to fetch
     * all mentorship requests and relations
     * @return of a list of [Relationship]
     */
    fun getAllRelationsAndRequests(): LiveData<Resource<List<Relationship>>> {
        return object : NetworkBoundResource<List<Relationship>, List<Relationship>>() {
            override fun saveCallResult(item: List<Relationship>) =
                relationshipDao.insertRelationships(item)

            override fun shouldFetch(data: List<Relationship>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb() = relationshipDao.findAll()

            override fun createCall() = apiManager.relationService.getAllRelationships()

        }.asLiveData()
    }

    /**
     * This will call a method from RelationService interface to accept a mentorship request
     * @param relationId id of the request being accepted
     */
    suspend fun acceptRelationship(relationId: Int) = apiManager.relationService.acceptRelationship(
        relationId
    )

    /**
     * This will call a method from RelationService interface to reject a mentorship request
     * @param relationId id of the request being rejected
     */
    suspend fun rejectRelationship(relationId: Int) = apiManager.relationService.rejectRelationship(
        relationId
    )

    /**
     * This will call a method from RelationService interface to delete a mentorship request
     * @param relationId id of the request being deleted
     */
    suspend fun deleteRelationship(relationId: Int) = apiManager.relationService.deleteRelationship(
        relationId
    )

    /**
     * This will call a method from RelationService interface to cancel a mentorship relation
     * @param relationId id of the request being canceled
     */
    suspend fun cancelRelationship(relationId: Int) = apiManager.relationService.cancelRelationship(
        relationId
    )

    /**
     * This will call a method from RelationService interface to send mentorship request
     * @param relationshipRequest object with fields to send a mentorship request
     */
    suspend fun sendRequest(relationshipRequest: RelationshipRequest) =
        apiManager.relationService.sendRequest(
            relationshipRequest
        )

    /**
     * This will call a method from RelationService interface to get accepted mentorship relation
     */
    suspend fun getCurrentRelationship() = apiManager.relationService.getCurrentRelationship()
}
