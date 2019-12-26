package org.systers.mentorship.dsl

import android.util.Log
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

fun Exception.handleNetworkExceptionWithMessage(tag: String): String {
    val context = MentorshipApplication.getContext()

    return when (this) {
        is IOException -> {
            context.getString(R.string.error_please_check_internet)
        }
        is TimeoutException -> {
            context.getString(R.string.error_request_timed_out)
        }
        is HttpException -> {
            CommonUtils.getErrorResponse(this).message
        }
        else -> {
            printStackTrace()
            Log.e(tag, localizedMessage)
            context.getString(R.string.error_something_went_wrong)
        }
    }
}
