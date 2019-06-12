package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.view.fragments.TasksFragment
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel: ViewModel() {
    var taskList = mutableListOf<String>()
    var completedTaskList = mutableListOf<String>()

    private val taskDataManager: TaskDataManager = TaskDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

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

        /**
         * Sends new task via API
         */
        taskDataManager.createNewTask(requestId, taskRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(customResponse: CustomResponse) {
                        message = customResponse.message ?: MentorshipApplication.getContext()
                                .getString(R.string.new_task_added_successful)
                        successful.value = true
                    }
                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(throwable).message.toString()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(TasksFragment.TAG, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }
                    override fun onComplete() {
                    }
                })
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
