package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Comments
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {

    @GET("/mentorship_relation/{relation_id}/task/{task_id}/comments/")
    fun getAllCommentsFromMentorshipRelation(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int
    ): Observable<List<Comments>>
}