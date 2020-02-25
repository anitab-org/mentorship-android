package org.systers.mentorship.remote.customAdapter

import android.util.Log
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

class CustomObservable<R>(private val call: Call<R>) {

    var TAG = CustomObservable::class.java.simpleName
    /**The run method is used to synchronously issue out network requests,
     * and pass results to the response handler
     */
    fun run(responseHandler: (R?, Throwable?) -> Unit) {
        // run in the same thread
        try {
            // call and handle response
            val response = call.execute()
            handleResponse(response, responseHandler)

        } catch (t: IOException) {
            responseHandler(null, t)
        }
    }

    /**The process method is used to asynchronously issue out network requests,
     * and pass the results to the response handler
     */
    fun process(responseHandler: (R?, Throwable?) -> Unit) {
        // define callback
        val callback = object : Callback<R> {

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val message = when (throwable) {
                    is IOException -> {
                        MentorshipApplication.getContext()
                                .getString(org.systers.mentorship.R.string.error_please_check_internet)
                    }
                    is TimeoutException -> {
                        MentorshipApplication.getContext()
                                .getString(org.systers.mentorship.R.string.error_request_timed_out)
                    }
                    is HttpException -> {
                        CommonUtils.getErrorResponse(throwable).message
                    }
                    else -> {
                        Log.e(TAG, throwable.localizedMessage)
                        MentorshipApplication.getContext()
                                .getString(org.systers.mentorship.R.string.error_something_went_wrong)
                    }
                }
                responseHandler(null, Exception(message, throwable))
            }



            override fun onResponse(call: Call<R>?, r: Response<R>?) =
                    handleResponse(r, responseHandler)
        }

        // enqueue network call
        call.enqueue(callback)
    }

    private fun handleResponse(response: Response<R>?, handler: (R?, Throwable?) -> Unit) {
        if (response?.isSuccessful == true) {
            handler(response.body(), null)
        } else {
            if (response?.code() in 400..511)
                handler(null, HttpException(response!!))

            else handler(response?.body(), null)
        }
    }
}
