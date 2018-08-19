package org.systers.mentorship.utils

import com.google.gson.Gson
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.HttpException


/**
 * Created by murad on 7/26/18.
 */
object CommonUtils {

    private val gson = Gson()

    /**
     * Extracts a CustomResponse object from a throwable
     * @param throwable from which the object is to be extracted
     * @return a CustomResponse object
     */
    fun getErrorResponse(@NonNull throwable: Throwable): CustomResponse {
        val httpException = throwable as HttpException
        val response = httpException.response().errorBody()?.string()
        return gson.fromJson(response.toString(), CustomResponse::class.java)
    }
}