package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Comment
import org.systers.mentorship.models.CommentDisplayModel
import org.systers.mentorship.models.User

import org.systers.mentorship.remote.datamanager.CommentsDataManager
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.remote.requests.CreateTask
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class CommentViewModel: ViewModel() {

    val tag: String = CommentViewModel::class.java.simpleName

    // commentDataManager

    private val _successfulGet: MutableLiveData<List<CommentDisplayModel>?> = MutableLiveData()
    val successfulGet: LiveData<List<CommentDisplayModel>?> = _successfulGet

    private val _successfulAdd: MutableLiveData<Boolean> = MutableLiveData()
    val successfulAdd: LiveData<Boolean> = _successfulAdd

    private val commentDataManager: CommentsDataManager = CommentsDataManager()
    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var message: String
    private val list: MutableList<CommentDisplayModel> = mutableListOf()

    private fun getCommentModelObservable(commentDisplayModel: CommentDisplayModel): Observable<CommentDisplayModel> {
        return Observable.just(commentDisplayModel)
    }

    private fun makeCommentDisplayModel(comments: List<Comment>) {
        comments.forEach {
            var commentDisplayModel: CommentDisplayModel = CommentDisplayModel()
            commentDisplayModel.comment = it
            userDataManager.getUser(it.userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object: DisposableObserver<User>() {
                        override fun onNext(t: User) {
                            commentDisplayModel.user = t
                            list.add(commentDisplayModel)
                        }

                        override fun onError(e: Throwable) {}

                        override fun onComplete() {
                            _successfulGet.value = list.sortedBy {
                                it.comment?.creationDate
                            }
                        }

                    })
        }
    }

    /**
     * This function lists all tasks from the mentorship relation
     */
    @SuppressLint("CheckResult")
    fun getComments(relationId: Int, taskId: Int) {
        Log.i("viewmodel", "Inside get Comments")
        commentDataManager.getAllComments(relationId, taskId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Comment>>() {
                    override fun onNext(commentListResponse: List<Comment>) {
                        makeCommentDisplayModel(commentListResponse)
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
                                message = throwable.message()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(tag, throwable.localizedMessage)
                            }
                        }
                        _successfulAdd.value = false
                    }
                    override fun onComplete() {
                    }
                })
    }

    /**
     * This function helps in adds a new task to the task list
     * @param relationId relation for which task is to be added
     * @param createTask to serialize task description
     */
    @SuppressLint("CheckResult")
    fun addComment(relationId: Int, taskId: Int, createComment: CreateComment) {
        commentDataManager.addComment(relationId, taskId, createComment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(taskListResponse: CustomResponse) {
                        _successfulAdd.value = true
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
                                message = throwable.message()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(tag, throwable.localizedMessage)
                            }
                        }
                        _successfulAdd.value = false
                    }
                    override fun onComplete() {
                    }
                })
    }

    fun updateComment() {}
}