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
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for Tasks Screen
 */
class TasksViewModel(private val relation: Relationship) : ViewModel() {

    private val TAG = TasksViewModel::class.java.simpleName

    lateinit var tasks: List<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()
    val getSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val createSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val completeSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val deleteSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * This function lists all tasks from the mentorship relation
     */
    @SuppressLint("CheckResult")
    fun getTasks() {
        taskDataManager.getAllTasks(relation.id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Task>>() {
                override fun onNext(taskListResponse: List<Task>) {
                    tasks = taskListResponse
                    getSuccessful.value = true
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
                    getSuccessful.value = false
                }

                override fun onComplete() {}
            })
    }

    /**
     * Calls [TaskDataManager.createTask] to create a new task
     * @param taskName title of the new task
     */
    @SuppressLint("CheckResult")
    fun createTask(taskName: String) {
        val taskRequest = TaskRequest(description = taskName)

        taskDataManager.createTask(relation.id, taskRequest)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<CustomResponse>() {
                override fun onNext(t: CustomResponse) {
                    getTasks()
                    createSuccessful.value = true
                }

                override fun onError(e: Throwable) {
                    when (e) {
                        is IOException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_please_check_internet)
                        }
                        is TimeoutException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_request_timed_out)
                        }
                        is HttpException -> {
                            message = CommonUtils.getErrorResponse(e).message
                        }
                        else -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                            Log.e(TAG, e.localizedMessage)
                        }
                    }
                    createSuccessful.value = false
                }

                override fun onComplete() {}
            })
    }

    /**
     * Calls [TaskDataManager.completeTask] to mark the task with specified as completed
     * @param taskId id of the task to be marked as completed
     */
    @SuppressLint("CheckResult")
    fun completeTask(taskId: Int) {
        taskDataManager.completeTask(relation.id, taskId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<CustomResponse>() {
                override fun onNext(t: CustomResponse) {
                    getTasks()
                    completeSuccessful.value = true
                }

                override fun onError(e: Throwable) {
                    when (e) {
                        is IOException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_please_check_internet)
                        }
                        is TimeoutException -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_request_timed_out)
                        }
                        is HttpException -> {
                            message = CommonUtils.getErrorResponse(e).message
                        }
                        else -> {
                            message = MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                            Log.e(TAG, e.localizedMessage)
                        }
                    }
                    completeSuccessful.value = false
                }

                override fun onComplete() {}
            })
    }

    /**
     * Calls [TaskDataManager.deleteTask] to delete the task with specified taskId
     * @param taskId id of the task to be deleted
     */
    @SuppressLint("CheckResult")
    fun deleteTask(taskId: Int) {
        taskDataManager.deleteTask(relation.id, taskId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<CustomResponse>() {
                override fun onNext(relationship: CustomResponse) {
                    getTasks()
                    deleteSuccessful.value = true
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
                }

                override fun onComplete() {}
            })
    }
}
