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
import org.systers.mentorship.models.Comments
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.datamanager.CommentDataManager
import org.systers.mentorship.remote.datamanager.TaskDataManager
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class CommentsViewModel: ViewModel() {

    var tag = CommentsViewModel::class.java.simpleName!!

    lateinit var commentsList: List<Comments>

    private val commentsDataManager: CommentDataManager = CommentDataManager()
    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var message: String

    @SuppressLint("CheckResult")
    fun getComments(relationId: Int, taskId: Int) {
        commentsDataManager.getAllComments(relationId, taskId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Comments>>() {
                    override fun onNext(commentsListResponse: List<Comments>) {
                        commentsList = commentsListResponse
                        successfulGet.value = true
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
                                Log.e(tag, throwable.localizedMessage)
                            }
                        }
                        successfulGet.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}