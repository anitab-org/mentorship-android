package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.TaskDescription
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel : ViewModel() {

    private val TAG = TasksViewModel::class.java.simpleName

    lateinit var taskList: MutableList<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()
    val successful: MutableLiveData<Boolean> = MutableLiveData()
    val successfulCreate: MutableLiveData<Boolean> = MutableLiveData()
    val successfulComplete: MutableLiveData<Boolean> = MutableLiveData()
    val successfulDelete: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    var relationId = 0

    /**
     * This function lists all tasks from the mentorship relation
     */
    @SuppressLint("CheckResult")
    fun getTasks() {
        taskDataManager.getAllTasks(relationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Task>>() {
                    override fun onNext(taskListResponse: List<Task>) {
                        taskList = taskListResponse as MutableList<Task>
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
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * This function helps in adds a new task to the task list
     * @param description title of the new task
     */
    @SuppressLint("CheckResult")
    fun addTask(description: TaskDescription) {
        taskDataManager.createTask(relationId, description)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(customResponse: CustomResponse) {
                        // needed in order to update the list (check out the observer)
                        successful.value = null
                        getTasks()
                        message = customResponse.message
                        successfulCreate.value = true
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
                                message = CommonUtils.getErrorResponse(throwable).message
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        successfulCreate.value = false
                    }

                    override fun onComplete() {}
                })
    }

    /**
     * This function helps in updating completed tasks
     * @param taskId id of the task that is clicked
     * @param isChecked boolean value to specify if the task was marked or unmarked
     */
    @SuppressLint("CheckResult")
    fun updateTask(taskId: Int, isChecked: Boolean) {
        if (isChecked) {
            taskDataManager.completeTask(relationId, taskId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<CustomResponse>() {
                        override fun onNext(customResponse: CustomResponse) {
                            // needed in order to update the list (check out the observer)
                            successful.value = null
                            getTasks()
                            message = customResponse.message
                            successfulComplete.value = true
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
                                    message = CommonUtils.getErrorResponse(throwable).message
                                }
                                else -> {
                                    message = MentorshipApplication.getContext()
                                            .getString(R.string.error_something_went_wrong)
                                    Log.e(TAG, throwable.localizedMessage)
                                }
                            }
                            successfulComplete.value = false
                        }

                        override fun onComplete() {}
                    })
        } else {
            taskDataManager.deleteTask(relationId, taskId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<CustomResponse>() {
                        override fun onNext(customResponse: CustomResponse) {
                            // needed in order to update the list (check out the observer)
                            successful.value = null
                            getTasks()
                            message = customResponse.message
                            successfulDelete.value = true
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
                                    message = CommonUtils.getErrorResponse(throwable).message
                                }
                                else -> {
                                    message = MentorshipApplication.getContext()
                                            .getString(R.string.error_something_went_wrong)
                                    Log.e(TAG, throwable.localizedMessage)
                                }
                            }
                            successfulDelete.value = false
                        }

                        override fun onComplete() {}
                    })
        }
    }

}