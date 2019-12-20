package org.systers.mentorship.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents a task related to a mentorship
 * relation.
 *
 * @param id The id of this task
 * @param description The description of this task
 * @param isDone Represents whether this task has been completed
 * @param createdAt Unix timestamp of when this task was created
 * @param completedAt Unix timestamp of when this task was completed
 */

@Parcelize
data class Task(
        val id: Int,
        val description: String,
        @SerializedName("is_done")
        val isDone: Boolean,
        @SerializedName("created_at")
        val createdAt: Float,
        @SerializedName("completed_at")
        val completedAt: Float
): Parcelable

