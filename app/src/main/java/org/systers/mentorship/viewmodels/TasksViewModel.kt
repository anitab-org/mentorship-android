package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val taskDataManager: TaskDataManager = TaskDataManager()

    lateinit var tasksList: List<Task>
    /**
     * This function lists all tasks from the mentorship relation
     */
    fun getTasks(relationId: Int) =
        observe(taskDataManager.getAllTasks(relationId), { tasksList = it })

    /**
     * This function helps in adds a new task to the task list
     * @param taskName title of the new task
     */
    fun addTask(taskName: String) {
        //TODO: Update the backend
    }

    /**
     * This function helps in updating completed tasks
     * @param taskId id of the task that is clicked
     * @param isChecked boolean value to specify if the task was marked or unmarked
     */
    fun updateTask(taskId: Int, isChecked: Boolean){
        if(isChecked) {
            //completedTaskList.add(taskList.get(taskId))
            //TODO: Update the backend
        }
        else {
            //completedTaskList.remove(taskList.get(taskId))
            //TODO: Update the backend
        }
    }
}
