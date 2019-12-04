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
class TasksViewModel(val mentorshipRelation: Relationship) : ViewModel() {

    private val TAG = TasksViewModel::class.java.simpleName

    lateinit var tasksList: List<Task>

    private val taskDataManager: TaskDataManager = TaskDataManager()
    val relationFetchSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * This function fetches all tasks from the mentorship relation
     * It should be triggered after every operation (e.g [addTask] or [deleteTask]) to keep
     * the task list updated
     */
    @SuppressLint("CheckResult")
    fun getTasks(relationId: Int) {
        taskDataManager.getAllTasks(relationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Task>>() {
                    override fun onNext(taskListResponse: List<Task>) {
                        tasksList = taskListResponse
                        relationFetchSuccessful.value = true
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
                        relationFetchSuccessful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * This function adds a new task to the task list
     * If the operation is successful, [getTasks] is automatically triggered
     * @param description description/title of the new task
     */
    @SuppressLint("CheckResult")
    fun addTask(description: String) {
        val taskRequest = TaskRequest(description)

        taskDataManager.createTask(mentorshipRelation.id, taskRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(relationship: CustomResponse) {
                        getTasks(mentorshipRelation.id)
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

    /**
     * This function deletes the specific task permamently from the task list
     * If the operation is successful, [getTasks] is automatically triggered
     * @param taskId mentoring task id
     */
    @SuppressLint("CheckResult")
    fun deleteTask(taskId: Int) {
        taskDataManager.deleteTask(mentorshipRelation.id, taskId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(relationship: CustomResponse) {
                        getTasks(mentorshipRelation.id)
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

    /**
     * This function marks the task as completed
     * If the operation is successful, [getTasks] is automatically triggered
     * @param taskId id of the task that is clicked
     * @param isChecked currently unused because API doesn't support "unchecking"
     */
    @SuppressLint("CheckResult")
    fun updateTask(taskId: Int, isChecked: Boolean) {
        taskDataManager.completeTask(mentorshipRelation.id, taskId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(response: CustomResponse) {
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