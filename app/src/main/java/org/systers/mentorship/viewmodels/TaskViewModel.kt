package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val appContext = MentorshipApplication.getContext()

    lateinit var tasksList: List<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()
    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * This function lists all tasks from the mentorship relation
     */
    fun getTasks(relationId: Int) = viewModelScope.launch {
        try {
            tasksList = taskDataManager.getAllTasks(relationId)
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }

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
    fun updateTask(taskId: Int, isChecked: Boolean) {
        if (isChecked) {
            //completedTaskList.add(taskList.get(taskId))
            //TODO: Update the backend
        } else {
            //completedTaskList.remove(taskList.get(taskId))
            //TODO: Update the backend
        }
    }
}
