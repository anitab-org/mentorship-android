package org.anitabmentorship.remote.datamanager

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.anitabmentorship.models.Task
import org.anitabmentorship.remote.ApiManager
import org.anitabmentorship.remote.requests.CreateTask
import org.anitabmentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Mentorship Task API
 */
class TaskDataManager(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    suspend fun getAllTasks(relationId: Int): List<Task> {
        return withContext(dispatcher) { apiManager.taskService.getAllTasksFromMentorshipRelation(relationId) }
    }

    /**
     * This will call a method from Taskservice interface to complete a task
     * @param relationId mentorship relation id
     * @return an Observable of [CustomResponse]
     */
    suspend fun completeTask(relationId: Int, taskId: Int): CustomResponse {
        return withContext(dispatcher) { apiManager.taskService.completeTaskFromMentorshipRelation(relationId, taskId) }
    }

    /**
     * This will call a method from Taskservice interface to add task for a relationship
     * @param relationId mentorship relation id
     * @param createTask object to serialize task description
     * @return an Observable of [CustomResponse]
     */
    suspend fun addTask(relationId: Int, createTask: CreateTask): CustomResponse {
        return withContext(dispatcher) { apiManager.taskService.addTaskToMentorshipRelation(relationId, createTask) }
    }
}
