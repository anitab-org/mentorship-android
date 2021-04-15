package org.systers.mentorship.remote.requests

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to changing user password
 * @param current_password the current password of the user
 * @param new_password new password which will replace the current password.
 */
@JsonClass(generateAdapter = true)
data class ChangePassword (
        val current_password: String,
        val new_password: String
)
