package org.systers.mentorship.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents a task related to a mentorship
 * relation.
 *
 * @param id The id of this task
 * @param description The description of this task
 * @param is_done Represents whether this task has been completed
 * @param created_at Unix timestamp of when this task was created
 * @param completed_at Unix timestamp of when this task was completed
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class Task(
        val id: Int,
        val description: String,
        val is_done: Boolean,
        val created_at: Float,
        val completed_at: Float
): Parcelable
