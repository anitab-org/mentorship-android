package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to register a new user
 * @param name represents the name of the new user
 * @param username represents the username of the new user, used for login
 * @param email represents the email of the new user, used for login
 * @param password represents the password of the new user, used for login
 * @param hasAcceptedTermsAndConditions is true if the user checked the terms and
 *                                      conditions checkbox, false if otherwise
 */
data class RegisterRequest (
        val name: String,
        val username: String,
        val email: String,
        val password: String,
        @SerializedName("terms_and_conditions_checked") val hasAcceptedTermsAndConditions: Boolean,
        @SerializedName("need_mentoring") val needsMentoring: Boolean,
        @SerializedName("available_to_mentor") val isAvailableToMentor: Boolean
)
