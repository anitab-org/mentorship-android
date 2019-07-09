package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import retrofit2.http.*

/**
 * This interface describes the methods related to Mentorship Task REST API
 */
interface TaskService {

    /**
     * This function gets all the tasks from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @return an observable instance of a list of [Task]s
     */
    @GET("mentorship_relation/{relation_id}/tasks")
    fun getAllTasksFromMentorshipRelation(@Path("relation_id") relationId: Int): Observable<List<Task>>
}