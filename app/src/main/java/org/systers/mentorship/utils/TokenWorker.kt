package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.responses.AuthToken
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/*
    * This class refreshes the access token automatically if it is expired
    *  and then puts it in the database.
    *  This will be called every hour.
 */
class TokenWorker(context: Context, workerParams: WorkerParameters)
    : Worker(context, workerParams) {

    private val TAG = TokenWorker::class.java.simpleName
    private val preferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()
    private var message = ""
    private var successful = true

    @SuppressLint("CheckResult")
    override fun doWork(): Result {
        if (getUnixTimestampInMilliseconds(preferenceManager.authExpiry) < System.currentTimeMillis()
                && getUnixTimestampInMilliseconds(preferenceManager.refreshExpiry) > System.currentTimeMillis()) {
            authDataManager.refreshToken()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<AuthToken>() {
                        override fun onNext(authToken: AuthToken) {
                            preferenceManager.putAuthToken(authToken)
                            successful = true
                        }

                        override fun onError(throwable: Throwable) {
                            when (throwable) {
                                is IOException ->
                                    message = MentorshipApplication.getContext()
                                            .getString(R.string.error_please_check_internet)
                                is TimeoutException ->
                                    message = MentorshipApplication.getContext()
                                            .getString(R.string.error_request_timed_out)
                                is HttpException ->
                                    message = CommonUtils.getErrorResponse(throwable).message
                                else -> {
                                    message = MentorshipApplication.getContext()
                                            .getString(R.string.error_something_went_wrong)
                                    Log.e(TAG, throwable.localizedMessage)
                                }
                            }
                            successful = false
                        }

                        override fun onComplete() {}
                    })
        } else return Result.failure()

        if (message.isNotEmpty())
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

        return if (successful) Result.success() else Result.retry()
    }

}