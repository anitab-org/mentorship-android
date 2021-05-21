package org.systers.mentorship.remote.datamanager

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
    suspend fun getAllTasks(relationId: Int): List<Task> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

    /**
     * This will call a method from Taskservice interface to complete a task
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    suspend fun completeTask(relationId: Int, taskId: Int): CustomResponse {
        return apiManager.taskService.completeTaskFromMentorshipRelation(relationId, taskId)
    }

    /**
     * This will call a method from Taskservice interface to add task for a relationship
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an Observable of [CustomResponse]
     */
    suspend fun addTask(relationId: Int, createTask: CreateTask): CustomResponse {
        return apiManager.taskService.addTaskToMentorshipRelation(relationId, createTask)
    }
}
