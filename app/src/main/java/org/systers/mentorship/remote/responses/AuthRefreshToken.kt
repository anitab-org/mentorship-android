package org.systers.mentorship.remote.responses

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to create an authentication token
 * @param authToken represents an authentication token
 * @param accessExpiry represents the authentication expiry timestamp
 * @param refreshToken represents an refresh token
 * @param refreshExpiry represents the refresh expiry timestamp
 */
data class AuthRefreshToken(@SerializedName("access_token") var authToken: String,
                            @SerializedName("access_expiry") var accessExpiry: Float,
                            @SerializedName("refresh_token") var refreshToken: String,
                            @SerializedName("refresh_expiry") var refreshExpiry: Float)
