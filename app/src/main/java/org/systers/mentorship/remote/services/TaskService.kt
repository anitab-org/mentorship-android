package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.*

/**
 * This interface describes the methods related to Mentorship Relation REST API
 */
interface TaskService {

    /**
     * This function performs creation of a task
     * @param taskRequest data required to save a task
     * @return an observable instance of [CustomResponse] with a proper error or success message
     */
    @POST("mentorship_relation/{request_id}/task")
    fun sendRequest(@Path("request_id") relationId: Int, @Body taskRequest: TaskRequest): Observable<CustomResponse>
}
