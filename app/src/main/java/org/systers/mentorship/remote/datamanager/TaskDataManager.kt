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
     * Calls a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    fun getAllTasks(relationId: Int): Observable<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * Calls a method from [org.systers.mentorship.remote.services.TaskService] interface
     * to create a new task
     * @param relationId id of the current relation the user is involved in
     * @param taskRequest data needed to create a request
     * @return an Observable of [CustomResponse]
     */
    fun createTask(relationId: Int, taskRequest: TaskRequest): Observable<CustomResponse> {
        return apiManager.taskService.createTask(relationId, taskRequest)
    }

    /**
     * Calls a method from [org.systers.mentorship.remote.services.TaskService] interface
     * to complete the task with specified id within current user's relation
     * @param requestId mentorship request id
     * @param taskId id of the task to mark as completed
     * @return an Observable of [CustomResponse]
     */
    fun completeTask(requestId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.completeTask(requestId, taskId)
    }

    /**
     * Calls a method from [org.systers.mentorship.remote.services.TaskService] interface
     * to *delete* the task with specified id within current user's relation
     * @param requestId mentorship request id
     * @param taskId id of the task to be deleted
     * @return an Observable of [CustomResponse]
     */
    fun deleteTask(requestId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.deleteTask(requestId, taskId)
    }
}
