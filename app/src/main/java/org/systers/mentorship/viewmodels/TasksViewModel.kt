package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.requests.CreateTask

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val taskDataManager: TaskDataManager = TaskDataManager()

    lateinit var tasksList: List<Task>

    val successfulAdd: MutableLiveData<Boolean> = MutableLiveData()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    /**
     * This function lists all tasks from the mentorship relation
     */
    fun getTasks(relationId: Int) =
        observe(taskDataManager.getAllTasks(relationId), { tasksList = it })

    /**
     * This function helps in adds a new task to the task list
     * @param taskName title of the new task
     */
    fun addTask(relationId: Int, taskName: String) =
        observe(taskDataManager.addTask(relationId, CreateTask(taskName)),
            success = { successfulAdd.value = true },
            failure = { successfulAdd.value = false },
            changeDefaultStatus = false)

    /**
     * This function helps in updating completed tasks
     * @param taskId id of the task that is clicked
     * @param isChecked boolean value to specify if the task was marked or unmarked
     * @param relationId id of relation
     */
    fun updateTask(relationId: Int, taskId: Int, isChecked: Boolean) {
        if (isChecked) {
            observe(taskDataManager.completeTask(relationId, taskId),
                success = { successfulUpdate.value = true },
                failure = { successfulUpdate.value = false },
                changeDefaultStatus = false
            )
        }
    }
}
