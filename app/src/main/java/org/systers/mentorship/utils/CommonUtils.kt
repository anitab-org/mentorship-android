package org.systers.mentorship.utils

import com.google.gson.Gson
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.HttpException


/**
 * Object to store utilities such as a [Gson] instance
 */
object CommonUtils {

    val gson = Gson()

    /**
     * Extracts a CustomResponse object from a throwable
     * @param throwable from which the object is to be extracted
     * @return a CustomResponse object
     */
    fun getErrorResponse(throwable: Throwable): CustomResponse {
        val httpException = throwable as HttpException
        val response = httpException.response()?.errorBody()?.string()
        response?.let {
            return gson.fromJson(it, CustomResponse::class.java)
        }
        return CustomResponse("Unknown error occurred")
    }
}
