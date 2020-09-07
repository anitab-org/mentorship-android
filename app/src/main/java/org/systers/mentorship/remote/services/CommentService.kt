package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.Comments
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {

    @GET("/mentorship_relation/{relation_id}/task/{task_id}/comments/")
    fun getAllCommentsFromMentorshipRelation(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int
    ): Observable<List<Comments>>

    @POST("/mentorship_relation/{relation_id}/task/{task_id}/comment")
    fun addCommentToMentorshipRelation(
            @Path("relation_id") relationId: Int,
            @Path("task_id") taskId: Int,
            @Body createComment: CreateComment
    ): Observable<CustomResponse>
}