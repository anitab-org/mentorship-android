package org.systers.mentorship.remote.responses

/**
 * Created by murad on 7/26/18.
 */
data class LoginResponse(var authToken: String? = null,
                         var expiry: Long? = null)