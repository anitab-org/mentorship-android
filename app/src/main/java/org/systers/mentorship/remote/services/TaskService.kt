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
     * Gets all the tasks from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @return an observable instance of a list of [Task]s
     */
    @GET("mentorship_relation/{relation_id}/tasks")
    fun getAllTasksFromMentorshipRelation(@Path("relation_id") relationId: Int): Observable<List<Task>>

    /**
     * Creates a new task all the tasks from a mentorship relation
     * @param requestId id of the mentorship relation request between two users
     * @param taskRequest data needed to create a request
     * @return an Observable of [CustomResponse]
     */
    @POST("mentorship_relation/{request_id}/task")
    fun createTask(@Path("request_id") requestId: Int, @Body taskRequest: TaskRequest): Observable<CustomResponse>

    /**
     * Marks the task with the specified id as done/completed.
     * @param requestId id of the mentorship relation request between two users
     * @param taskId id of the task to mark as completed
     * @return an Observable of [CustomResponse]
     */
    @PUT("mentorship_relation/{request_id}/task/{task_id}/complete")
    fun completeTask(@Path("request_id") requestId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>

    /**
     * Deletes the task with the specified id from a mentorship relation
     * @param requestId id of the mentorship relation request between two users
     * @param taskId id of the task to be deleted
     * @return an Observable of [CustomResponse]
     */
    @DELETE("mentorship_relation/{request_id}/task/{task_id}")
    fun deleteTask(@Path("request_id") requestId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>
}
