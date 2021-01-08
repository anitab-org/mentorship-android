package org.systers.mentorship.utils

import com.squareup.moshi.Moshi
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.JwtPayload
import retrofit2.HttpException


/**
 * Object to store utilities such as a [moshi] instance
 */
object CommonUtils {

    val moshi = Moshi.Builder().build()
    val errorAdapter = moshi.adapter(CustomResponse::class.java)
    val jwtadapter = moshi.adapter(JwtPayload::class.java)

    /**
     * Extracts a CustomResponse object from a throwable
     * @param throwable from which the object is to be extracted
     * @return a CustomResponse object
     */
    fun getErrorResponse(@NonNull throwable: Throwable): CustomResponse {
        val httpException = throwable as HttpException
        val response = httpException.response().errorBody()?.string()
        return errorAdapter.fromJson(response!!)!!
    }
}
