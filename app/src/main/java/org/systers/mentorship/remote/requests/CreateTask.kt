package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to create a comment
 * @param description the task description
 */
@JsonClass(generateAdapter = true)
data class CreateTask(
        val description: String
)
