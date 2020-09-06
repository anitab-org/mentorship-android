package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Comments
import org.systers.mentorship.remote.ApiManager

class CommentDataManager {

    private val apiManager = ApiManager.instance

    fun getAllComments(relationId: Int, taskId: Int): Observable<List<Comments>> =
            apiManager.commentService.getAllCommentsFromMentorshipRelation(relationId, taskId)
}