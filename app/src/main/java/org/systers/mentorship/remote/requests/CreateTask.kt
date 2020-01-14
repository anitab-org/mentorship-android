package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to create a new task
 * @param description description of the task
 */
data class CreateTask(
        @SerializedName("description") val description: String
)