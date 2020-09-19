package org.systers.mentorship.models

import android.os.Parcelable
import androidx.room.PrimaryKey
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
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val description: String,
        val isDone: Boolean,
        val createdAt: Float,
        val completedAt: Float
): Parcelable
