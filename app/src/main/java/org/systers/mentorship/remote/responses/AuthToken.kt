package org.systers.mentorship.remote.responses

/**
 * This data class represents all data necessary to create an authentication token
 * @param accessToken represents an authentication token
 * @param accessExpiry represents the expiry timestamp of accessToken
 * @param refreshToken represent refresh token
 * @param refreshExpiry represents expiry of refresh token
 */
data class AuthToken(var accessToken: String,
                     var accessExpiry: Float,
                     var refreshToken: String,
                     var refreshExpiry: Float)