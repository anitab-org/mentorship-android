package org.systers.mentorship.remote.responses

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to create a Github access token
 * @param accessToken represents the access token
 * @param token_type represents the token type
 */
data class GithubAccessToken(@SerializedName("access_token") var accessToken: String,
                             @SerializedName("token_type") var tokenType: String)
