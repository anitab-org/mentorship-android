package org.systers.mentorship.remote.calladapter

import android.util.Log
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException

class Simple<R>(private val call: Call<R>) {

    private val tag = Simple::class.java.simpleName

    /*
        Executes the call synchronously
     */
    fun run(responseHandler: (R?, Throwable?) -> Unit) =
            try {
                handleResponse(call.execute(), responseHandler)
            } catch (t: IOException) {
                responseHandler(null, t)
            }

    /*
        Executes the call asynchronously
     */
    fun process(responseHandler: (R?, Throwable?) -> Unit) =
            call.enqueue(object : Callback<R> {

                override fun onFailure(call: Call<R>, t: Throwable) {
                    val message = when (t) {
                        is IOException -> {
                            MentorshipApplication.getContext()
                                    .getString(org.systers.mentorship.R.string.error_please_check_internet)
                        }
                        is TimeoutException -> {
                            MentorshipApplication.getContext()
                                    .getString(org.systers.mentorship.R.string.error_request_timed_out)
                        }
                        is HttpException -> {
                            CommonUtils.getErrorResponse(t).message
                        }
                        else -> {
                            Log.e(tag, t.localizedMessage)
                            MentorshipApplication.getContext()
                                    .getString(org.systers.mentorship.R.string.error_something_went_wrong)
                        }
                    }
                    responseHandler(null, Exception(message, t))
                }

                override fun onResponse(call: Call<R>, r: Response<R>) =
                        handleResponse(r, responseHandler)

            })

    /*
        In case of errors this method will be executed
     */
    private fun handleResponse(response: Response<R>, handler: (R?, Throwable?) -> Unit) =
            when {
                response.isSuccessful -> handler(response.body(), null)
                response.code() in 400..511 -> handler(null, HttpException(response))
                else -> handler(response.body(), null)
            }

}