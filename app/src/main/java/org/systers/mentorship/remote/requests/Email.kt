package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary for a user email
 * @param email represents email
 *
 */
data class Email(@SerializedName("email") val email: String)

