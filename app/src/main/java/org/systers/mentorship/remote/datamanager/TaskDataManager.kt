package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.Simple
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
    fun getAllTasks(relationId: Int): Simple<List<Task>> {
        return apiManager.taskService.getAllTasksFromMentorshipRelation(relationId)
    }

}
