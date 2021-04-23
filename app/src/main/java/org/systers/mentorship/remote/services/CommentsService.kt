package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Comment
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.remote.requests.CreateTask
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.*

/**
 * This interface describes the methods related to Mentorship Task REST API
 */
interface CommentsService {

    /**
     * This function gets all the tasks from a mentorship relation
     * @param relationId id of the mentorship relation in question
     * @return an observable instance of a list of [Task]s
     */
    @GET("mentorship_relation/{relation_id}/task/{task_id}/comments")
    fun getAllCommentsForTask(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int
    ): Observable<List<Comment>>

    /**
     * This function marks a task from a mentorship relation as complete
     * @param relationId id of the mentorship relation in question
     * @param taskId id of the task in question
     * @return an observable instance of <CustomResponse>
     */
    @PUT("mentorship_relation/{relation_id}/task/{task_id}/comment/{comment_id}")
    fun updateCommentForTask(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int,
            @Path("comment_id") commentId: Int,
            @Body createComment: CreateComment
    ): Observable<CustomResponse>

    /**
     * This function creates a new task
     * @param relationId id of the mentorship relation in question
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an observable instance of <CustomResponse>
     */
    @POST("/mentorship_relation/{relation_id}/task/{task_id}/comment")
    fun addCommentToTask(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int,
            @Body createComment: CreateComment
    ): Observable<CustomResponse>
    //only 'description' field from Task model needed.
}
