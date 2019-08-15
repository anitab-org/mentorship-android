package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Task API
 */
class TaskDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    fun getAllTasks(relationId: Int): Observable<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * This will call a method from Taskservice interface to create a new task
     * @param relationId mentorship relation id
     * @param taskRequest object with fields to create a new task
     * @return an Observable of [CustomResponse]
     */
    fun createNewTask(relationId: Int, taskRequest: TaskRequest): Observable<CustomResponse> {
        return apiManager.taskService.createNewTask(relationId, taskRequest)
    }

}