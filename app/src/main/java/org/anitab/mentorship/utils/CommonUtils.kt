package org.anitab.mentorship.utils

import android.util.Log
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.util.concurrent.TimeoutException
import org.anitab.mentorship.MentorshipApplication
import org.anitab.mentorship.R
import org.anitab.mentorship.remote.responses.CustomResponse
import retrofit2.HttpException

/**
 * Object to store utilities such as a [Gson] instance
 */
object CommonUtils {

    val gson by lazy {
        Gson()
    }

    /**
     * Extracts the error message from an httpException
     * @param httpException from which the error message is to be extracted
     * @return a string containing the error message
     */
    private fun getErrorMessage(@NonNull httpException: HttpException): String {
        val response = httpException.response()?.errorBody()?.string()
            ?: return MentorshipApplication.getContext()
                .getString(R.string.error_something_went_wrong)
        return try {
            gson.fromJson(response, CustomResponse::class.java).message
        } catch (exception: JsonSyntaxException) {
            exception.printStackTrace()
            MentorshipApplication.getContext().getString(R.string.error_something_went_wrong)
        }
    }

    fun getErrorMessage(@NonNull throwable: Throwable, tag: String): String {
        return when (throwable) {
            is IOException -> {
                MentorshipApplication.getContext()
                    .getString(R.string.error_please_check_internet)
            }
            is TimeoutException -> {
                MentorshipApplication.getContext()
                    .getString(R.string.error_request_timed_out)
            }
            is HttpException -> {
                getErrorMessage(throwable)
            }
            else -> {
                Log.e(tag, throwable.localizedMessage)
                MentorshipApplication.getContext()
                    .getString(R.string.error_something_went_wrong)
            }
        }
    }
}
