package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to changing user password
 * @param currentPassword the current password of the user
 * @param newPassword new password which will replace the current password.
 */
data class ChangePassword (
        @SerializedName("current_password") val currentPassword: String,
        @SerializedName("new_password") val newPassword: String
)
