package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to changing user password
 * @param currentPassword represents the current password of the user
 * @param newPassword represents the new password which user want to use.
 */
data class ResetPassword (
        @SerializedName("current_password") val currentPassword: String,
        @SerializedName("new_password") val newPassword: String)