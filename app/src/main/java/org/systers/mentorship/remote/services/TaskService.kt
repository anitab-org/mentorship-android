package org.systers.mentorship.remote.services

import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.requests.CreateTask
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.customAdapter.CustomObservable
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
    fun getAllTasksFromMentorshipRelation(@Path("relation_id") relationId: Int): CustomObservable<List<Task>>

    /**
     * This function marks a task from a mentorship relation as complete
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task in question
     * @return an observable instance of <CustomResponse>
     */
    @PUT("mentorship_relation/{relation_id}/task/{task_id}/complete")
    fun completeTaskFromMentorshipRelation(@Path("relation_id") relationId: Int, @Path("task_id") taskId: Int): CustomObservable<CustomResponse>

    /**
     * This function creates a new task
     * @param relationId id of the mentorship relation in question
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an observable instance of <CustomResponse>
     */
    @POST("mentorship_relation/{relation_id}/task")
    fun addTaskToMentorshipRelation(@Path("relation_id") relationId: Int, @Body createTask: CreateTask): CustomObservable<CustomResponse>
    //only 'description' field from Task model needed.
}
