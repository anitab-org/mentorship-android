package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.remote.ApiManager

/**
 * This class represents the data manager related to Mentorship Task API
 */
class TaskDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call a method from Taskservice interface to fetch all tasks
     * @param relationId mentorship relation id
     */
    suspend fun getAllTasks(relationId: Int) = apiManager.taskService.getAllTasksFromMentorshipRelation(
            relationId)
}
