package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.requests.CreateTask
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
     * This function creates a new task in the mentorship relation
     * @param relationId id of the mentorship relation in question
     * @param taskDescription data required to create a task
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/{relation_id}/task")
    fun createTask(@Path("relation_id") relationId: Int, @Body taskDescription: CreateTask): Observable<CustomResponse>

    /**
     * This function updates the task of given Id to complete
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task in the mentorship relation
     * @return an Observable instance of [CustomResponse]
     */
    @PUT("mentorship_relation/{request_id}/task/{task_id}/complete")
    fun updateTaskToComplete(@Path("request_id") relationId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>
}