package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel: BaseViewModel() {

    override var TAG: String = TasksViewModel::class.java.simpleName

    lateinit var tasksList: List<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()

    /**
     * This function lists all tasks from the mentorship relation
     */
    @SuppressLint("CheckResult")
    fun getTasks(relationId: Int) {
        taskDataManager.getAllTasks(relationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = {
                            @Suppress("UNCHECKED_CAST")
                            tasksList = it as List<Task>
                            successful.value = true
                        },
                        error = {successful.value = false}
                ))
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