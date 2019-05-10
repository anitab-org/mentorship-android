package org.systers.mentorship.remote.responses

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to create an authentication token
 * @param authToken represents an authentication token
 * @param expiry represents the expiry timestamp
 */
data class AuthToken(@SerializedName("access_token") var authToken: String,
                     @SerializedName("access_expiry") var accessExpiry: Float)
