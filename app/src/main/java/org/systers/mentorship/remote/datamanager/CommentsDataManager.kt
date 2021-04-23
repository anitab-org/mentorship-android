package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Comment
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.remote.requests.CreateTask
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Task API
 */
class CommentsDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    fun getAllComments(relationId: Int, taskId: Int): Observable<List<Comment>> {
        return apiManager.commentsService.getAllCommentsForTask(relationId, taskId)
    }

    /**
     * This will call a method from Taskservice interface to complete a task
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    fun completeTask(relationId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.completeTaskFromMentorshipRelation(relationId,taskId)
    }

    /**
     * This will call a method from Taskservice interface to add task for a relationship
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an Observable of [CustomResponse]
     */
    fun addComment(relationId: Int, taskId: Int, createComment: CreateComment): Observable<CustomResponse> {
        return apiManager.commentsService.addCommentToTask(relationId, taskId, createComment)
    }

}
