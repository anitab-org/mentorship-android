package org.systers.mentorship.remote.requests

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to create a login
 * @param username represents username
 * @param password represents user password
 */
@JsonClass(generateAdapter = true)
data class Login(var username: String,
                 var password: String)
