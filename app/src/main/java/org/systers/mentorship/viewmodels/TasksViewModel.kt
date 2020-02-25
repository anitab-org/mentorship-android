package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.requests.CreateTask

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel : ViewModel() {

    var tag = TasksViewModel::class.java.simpleName!!

    lateinit var tasksList: List<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()
    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulAdd: MutableLiveData<Boolean> = MutableLiveData()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    var completeTasksList = {
        ->
        tasksList.filter { it.isDone }
    }
    var incompleteTasksList = {
        ->
        tasksList.filter { !it.isDone }
    }

    /**
     * This function lists all tasks from the mentorship relation
     */
    @SuppressLint("CheckResult")
    fun getTasks(relationId: Int) {
        taskDataManager.getAllTasks(relationId)
                .process { list, throwable ->
                    when (throwable) {
                        null -> {
                            when (list) {
                                null -> {
                                    successfulGet.postValue(false)
                                }
                                else -> {
                                    tasksList = list
                                    successfulGet.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successfulGet.postValue(false)
                        }
                    }
                }
    }

    /**
     * This function helps in adds a new task to the task list
     * @param relationId relation for which task is to be added
     * @param createTask to serialize task description
     */
    @SuppressLint("CheckResult")
    fun addTask(relationId: Int, createTask: CreateTask) {
        taskDataManager.addTask(relationId, createTask)
                .process { customResponse, throwable ->
                    when (throwable) {
                        null -> {
                            when (customResponse?.message) {
                                null -> {
                                    successfulAdd.postValue(false)
                                }
                                else -> {
                                    message = customResponse.message
                                    successfulAdd.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successfulAdd.postValue(false)
                        }
                    }

                }
    }

    /**
     * This function helps in updating completed tasks
     * @param taskId id of the task that is clicked
     * @param isChecked boolean value to specify if the task was marked or unmarked
     * @param relationId id of relation
     */
    @SuppressLint("CheckResult")
    fun updateTask(taskId: Int, isChecked: Boolean, relationId: Int) {
        if (isChecked) {
            taskDataManager.completeTask(relationId, taskId)
                    .process { customResponse, throwable ->
                        when (throwable) {
                            null -> {
                                when (customResponse?.message) {
                                    null -> {
                                        successfulUpdate.postValue(false)
                                    }
                                    else -> {
                                        message = customResponse.message
                                        successfulUpdate.postValue(true)
                                    }
                                }
                            }
                            else -> {
                                message = throwable.localizedMessage
                                successfulUpdate.postValue(false)
                            }
                        }

                    }
        } else {
            //completedTaskList.remove(taskList.get(taskId))
            //TODO: Update the backend
        }
    }
}
