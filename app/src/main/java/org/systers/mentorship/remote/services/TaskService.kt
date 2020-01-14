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
     * This function creates a new task from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @param task body of the task to be created
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/{relation_id}/task")
    fun createNewTaskFromMentorshipRelation(@Path("relation_id") relationId: Int, @Body task: TaskRequest): Observable<CustomResponse>

    /**
     * This function completes a task from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task to be completed
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @PUT("mentorship_relation/{relation_id}/task/{task_id}/complete")
    fun completeTaskFromMentorshipRelation(@Path("relation_id") relationId: Int, @Path("task_id") taskId: Int): Observable<CustomResponse>
}