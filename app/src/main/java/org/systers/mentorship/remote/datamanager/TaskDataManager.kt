package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.TaskDescription
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Task API
 */
class TaskDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from TaskService interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    fun getAllTasks(relationId: Int): Observable<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * This will call a method from TaskService interface to create a task
     * @param relationId mentorship relation id
     * @param description title of the new task
     * @return an Observable of [CustomResponse]
     */
    fun createTask(relationId: Int, description: TaskDescription) =
            apiManager.taskService.createTask(relationId, description)

    /**
     * This will call a method from TaskService interface to complete the task
     * @param relationId mentorship relation id
     * @param taskId id of the task
     * @return an Observable of [CustomResponse]
     */
    fun completeTask(relationId: Int, taskId: Int) =
            apiManager.taskService.completeTask(relationId, taskId)

    /**
     * This will call a method from TaskService interface to delete the task
     * @param relationId mentorship relation id
     * @param taskId id of the task
     * @return an Observable of [CustomResponse]
     */
    fun deleteTask(relationId: Int, taskId: Int) =
            apiManager.taskService.deleteTask(relationId, taskId)

}