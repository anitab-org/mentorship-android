package org.systers.mentorship.remote.requests

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to register a new user
 * @param name represents the name of the new user
 * @param username represents the username of the new user, used for login
 * @param email represents the email of the new user, used for login
 * @param password represents the password of the new user, used for login
 * @param terms_and_conditions_checked is true if the user checked the terms and
 *                                      conditions checkbox, false if otherwise
 * @param need_mentoring is true if user wants to be mentored
 * @param available_to_mentor is true if user is available as mentor

 */
@JsonClass(generateAdapter = true)
data class Register (
        val name: String,
        val username: String,
        val email: String,
        val password: String,
        val terms_and_conditions_checked: Boolean,
        val need_mentoring: Boolean,
        val available_to_mentor: Boolean)
