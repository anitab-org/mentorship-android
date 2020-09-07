package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Comments
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.remote.responses.CustomResponse

class CommentDataManager {

    private val apiManager = ApiManager.instance

    fun getAllComments(relationId: Int, taskId: Int): Observable<List<Comments>> =
            apiManager.commentService.getAllCommentsFromMentorshipRelation(relationId, taskId)

    fun addComment(relationId: Int, taskId: Int, createComment: CreateComment): Observable<CustomResponse> =
            apiManager.commentService.addCommentToMentorshipRelation(relationId, taskId, createComment)
}