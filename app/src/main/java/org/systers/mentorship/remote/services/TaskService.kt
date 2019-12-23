package org.systers.mentorship.remote.services

import org.systers.mentorship.models.Task
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This interface describes the methods related to Mentorship Task REST API
 */
interface TaskService {

    /**
     * This function gets all the tasks from a mentorship relation
     * @param relationId id of the mentorship relation in question
     */
    @GET("mentorship_relation/{relation_id}/tasks")
    suspend fun getAllTasksFromMentorshipRelation(@Path("relation_id") relationId: Int): List<Task>
}
