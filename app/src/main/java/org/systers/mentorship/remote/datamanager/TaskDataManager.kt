package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Task API
 */
class TaskDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from [org.systers.mentorship.remote.services.TaskService] interface
     * to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [List] of [Task]s
     */
    fun getAllTasks(relationId: Int): Observable<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * This will call a method from [org.systers.mentorship.remote.services.TaskService] interface
     * to complete the task with specified id within current user's relation
     * @param requestId mentorship request id
     * @param taskId mentoring task id
     * @return an Observable of [CustomResponse]
     */
    fun completeTask(requestId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.completeTask(requestId, taskId)
    }
}