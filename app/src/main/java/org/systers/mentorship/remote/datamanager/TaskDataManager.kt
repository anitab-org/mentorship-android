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
     * This will call a method from Task Service interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [List<Task>]
     */
    fun getAllTasks(relationId: Int): Observable<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * This will call a method from Task Service interface to create a new task
     * @param relationId mentorship relation id
     * @param task body of the task to be created
     * @return an Observable of [CustomResponse]
     */
    fun createNewTask(relationId: Int, task: TaskRequest): Observable<CustomResponse> {
        return apiManager.taskService.createNewTaskFromMentorshipRelation(relationId, task)
    }

    /**
     * This will call a method from Task Service interface to complete the task
     * @param relationId mentorship relation id
     * @param taskId id of the task to be completed
     * @return an Observable of [CustomResponse]
     */
    fun completeTask(relationId: Int, taskId: Int): Observable<CustomResponse> {
        return apiManager.taskService.completeTaskFromMentorshipRelation(relationId, taskId)
    }

}