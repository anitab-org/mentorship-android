package org.systers.mentorship.remote

import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeoutException

/**
 * Custom transformer, to perform network calls and handle the response.
 * */
class Custom<T>(private val call: Call<T>) {

    /**
     * This method runs asynchronously to perform network calls.
     * */
    fun process (responseHandler: (T?, Throwable?) -> Unit) {
        val callback = object : Callback<T> {

            override fun onFailure(call: Call<T>, throwable: Throwable) {

                var message: String = when (throwable) {
                    is IOException -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_please_check_internet)
                    }
                    is TimeoutException -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_request_timed_out)
                    }
                    is HttpException -> {
                        CommonUtils.getErrorResponse(throwable).message
                    }
                    else -> {
                        MentorshipApplication.getContext()
                                .getString(R.string.error_something_went_wrong)
                    }
                }
                responseHandler(null, Exception(message, throwable))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(response, responseHandler)
            }
        }
        // Enqueuing network call
        call.enqueue(callback)
    }

    /**
     * This method handles the response as per the requirement, i.e. successful or unsuccessful.
     * */
    private fun handleResponse(response: Response<T>, responseHandler: (T?, Throwable?) -> Unit) {

        if (response.isSuccessful) {
            responseHandler(response.body(), null)
        } else {
            if (response.code() in 400..511) {
                /**
                 * Extracting message from response error body to display it to the user.
                 * */
                val errorMsg = CommonUtils.gson.fromJson(response.errorBody().toString(), CustomResponse::class.java).message
                responseHandler(null, Exception(errorMsg))
            } else {
                responseHandler(response.body(), null)
            }
        }
    }
}
