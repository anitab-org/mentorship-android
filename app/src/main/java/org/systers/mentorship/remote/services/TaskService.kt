package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.TaskDescription
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
     * This function creates a new task
     * @param relationId id of the mentorship relation in question
     * @param description title of the new task
     * @return an observable instance of a [CustomResponse]
     */
    @POST("mentorship_relation/{relation_id}/task")
    fun createTask(@Path("relation_id") relationId: Int, @Body description: TaskDescription): Observable<CustomResponse>

    /**
     * This function completes the task
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task
     * @return an observable instance of a [CustomResponse]
     */
    @PUT("mentorship_relation/{relation_id}/task/{task_id}/complete")
    fun completeTask(@Path("relation_id") relationId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>

    /**
     * This function deletes the task
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task
     * @return an observable instance of a [CustomResponse]
     */
    @DELETE("mentorship_relation/{relation_id}/task/{task_id}")
    fun deleteTask(@Path("relation_id") relationId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>

}