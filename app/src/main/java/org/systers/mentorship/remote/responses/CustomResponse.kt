package org.systers.mentorship.remote.responses

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to create a custom response
 * @param message represents a message
 */
@JsonClass(generateAdapter = true)
data class CustomResponse(var message: String)
