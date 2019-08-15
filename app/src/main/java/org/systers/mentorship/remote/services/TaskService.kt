package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse
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

    /**
     * This function performs creation of a task
     * @param taskRequest data required to save a task
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/{request_id}/task")
    fun createNewTask(@Path("request_id") relationId: Int, @Body taskRequest: TaskRequest): Observable<CustomResponse>
}