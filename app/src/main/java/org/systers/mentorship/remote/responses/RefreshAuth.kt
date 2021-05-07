package org.systers.mentorship.remote.responses

/**
 * This data class represents all data received from refresh Endpoint of the api
 * @param accessToken represents an authentication token
 * @param accessExpiry represents the expiry timestamp of accessToken
 */
data class RefreshAuth (
        var accessToken: String,
        var accessExpiry: Float
)