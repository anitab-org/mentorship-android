package org.systers.mentorship.viewmodels

import android.arch.lifecycle.ViewModel
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel: ViewModel() {
    var taskList = mutableListOf<String>()
    var completedTaskList = mutableListOf<String>()

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
    fun addTask(taskName: String) {
        taskList.add(taskName)
        //TODO: Update the backend
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
