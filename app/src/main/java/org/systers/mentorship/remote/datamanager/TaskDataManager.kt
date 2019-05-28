package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Relation API
 */
class TaskDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from Taskservice interface to create a new task
     * @param relationId mentorship relation id
     * @param taskRequest object with fields to create a new task
     * @return an Observable of [CustomResponse]
     */
    fun sendRequest(relationId: Int, taskRequest: TaskRequest): Observable<CustomResponse> {
        return apiManager.taskService.sendRequest(relationId, taskRequest)
    }

}
