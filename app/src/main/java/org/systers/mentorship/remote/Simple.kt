package org.systers.mentorship.remote

import android.util.Log
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * A thin wrapper around [retrofit2.Call].
 */
class Simple<T>(private val call: Call<T>) {

    /**
     * Executes the call on the same thread.
     */
    fun run(responseHandler: (T?, Throwable?) -> Unit) {
        try {
            val response = call.execute()
            handleResponse(response, responseHandler)
        } catch (exception: IOException) {
            responseHandler(null, exception)
        }
    }

    /**
     * Executes the call asynchronously.
     */
    fun process(responseHandler: (T?, Throwable?) -> Unit) {

        val callback = object : Callback<T> {
            override fun onFailure(call: Call<T>, exception: Throwable) {

                val message: String = when (exception) {
                    is IOException -> {
                        MentorshipApplication.getContext()
                            .getString(org.systers.mentorship.R.string.error_please_check_internet)
                    }
                    is TimeoutException -> {
                        MentorshipApplication.getContext()
                            .getString(org.systers.mentorship.R.string.error_request_timed_out)
                    }
                    is HttpException -> {
                        CommonUtils.getErrorResponse(exception).message
                    }
                    else -> {
                        Log.e("Simple", exception.localizedMessage)
                        MentorshipApplication.getContext()
                            .getString(org.systers.mentorship.R.string.error_something_went_wrong)

                    }
                }

                responseHandler(null, Exception(message, exception ))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) =
                handleResponse(response, responseHandler)
        }

        call.enqueue(callback)
    }

    /**
     * Method handling non-successful calls and calls that have error HTTP response code
     */
    private fun handleResponse(response: Response<T>, handler: (T?, Throwable?) -> Unit) {
        if (response.isSuccessful) {
            handler(response.body(), null)
        } else {
            if (response.code() in 400..511) handler(null, HttpException(response))
            else handler(response.body(), null)

        }
    }
}
