package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.CreateTask
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
     * This will call a method from Taskservice to create a new task in the mentorship relation
     * @param relationId mentorship relation id
     * @param taskDescription data required to create a task
     * @return an Observable of [CustomResponse]
     */
    fun createTask(relationId: Int, taskDescription: CreateTask): Observable<CustomResponse>
    { return apiManager.taskService.createTask(relationId, taskDescription) }

    /**
     * This will call a method from Taskservice interface to update a task to complete
     * @param relationId mentorship relation id
     * @param taskId mentorship relation's task's id
     * @return an Observable of [CustomResponse]
     */
    fun updateTaskToComplete(relationId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.updateTaskToComplete(relationId, taskId)
    }
}