package org.systers.mentorship.utils

import com.google.gson.Gson
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
            .toString(16)
            .padStart(32, '0')
}

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
    fun getErrorResponse(@NonNull throwable: Throwable): CustomResponse {
        val httpException = throwable as HttpException
        val response = httpException.response().errorBody()?.string()
        return gson.fromJson(response.toString(), CustomResponse::class.java)
    }
}
