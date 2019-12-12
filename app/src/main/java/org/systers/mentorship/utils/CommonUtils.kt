package org.systers.mentorship.utils

import android.util.TypedValue
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import io.reactivex.annotations.NonNull
import org.systers.mentorship.models.User
import org.systers.mentorship.models.UserCopy
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.HttpException

@BindingAdapter("android:background")
fun TextInputEditText.changeBackground(edit: Boolean) {
    val typedValue = TypedValue()
    if (edit) {
        context.theme.resolveAttribute(android.R.attr.editTextBackground, typedValue, true)
        setBackgroundResource(typedValue.resourceId)
    } else
        background = null
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

fun User.toUserCopy() = UserCopy(
        id ?: -1,
        username ?: "",
        name ?: "",
        email ?: "",
        bio ?: "",
        location ?: "",
        occupation ?: "",
        organization ?: "",
        interests ?: "",
        skills ?: "",
        needsMentoring ?: true,
        isAvailableToMentor ?: true,
        slackUsername ?: ""
)

fun UserCopy.toUser() = User(
        id,
        username,
        name,
        email,
        bio,
        location,
        occupation,
        organization,
        interests,
        skills,
        needsMentoring,
        isAvailableToMentor,
        slackUsername
)