package org.systers.mentorship.remote.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by murad on 7/26/18.
 */
data class LoginResponse(@SerializedName("access_token") var authToken: String,
                         var expiry: Float)