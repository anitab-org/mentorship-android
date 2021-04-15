package org.systers.mentorship.remote.responses

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to create an authentication token
 * @param access_token represents an authentication token
 * @param access_expiry represents the expiry timestamp
 */
@JsonClass(generateAdapter = true)
data class AuthToken(var access_token: String,
                     var access_expiry: Float)
