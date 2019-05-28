package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.requests.TaskRequest

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel: ViewModel() {
    var taskList = mutableListOf<String>()
    var completedTaskList = mutableListOf<String>()

    private val taskDataManager: TaskDataManager = TaskDataManager()

    /**
     * This function lists all tasks from the mentorship relation
     */
    fun getTasks() {
        taskList.add(MentorshipApplication.getContext().getString(R.string.model_task_1))
        taskList.add(MentorshipApplication.getContext().getString(R.string.model_task_2))
        taskList.add(MentorshipApplication.getContext().getString(R.string.model_task_3))
        //TODO: Fetch the list from the backend
    }

    /**
     * This function helps in adds a new task to the task list
     * @param taskName title of the new task
     */
    fun addTask(requestId: Int, taskName: String) {

        taskList.add(taskName)

        val taskRequest = TaskRequest(description = taskName);
        taskDataManager.createNewTask(requestId, taskRequest);

    }

    /**
     * This function helps in updating completed tasks
     * @param taskId id of the task that is clicked
     * @param isChecked boolean value to specify if the task was marked or unmarked
     */
    fun updateTask(taskId: Int, isChecked: Boolean){
        if(isChecked) {
            completedTaskList.add(taskList.get(taskId))
            //TODO: Update the backend
        }
        else {
            completedTaskList.remove(taskList.get(taskId))
            //TODO: Update the backend
        }
    }
}
