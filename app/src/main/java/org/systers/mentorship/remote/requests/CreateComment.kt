package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all data necessary to create a comment
 * @param description the task description
 */
data class CreateComment(@SerializedName("comment") val comment: String)
