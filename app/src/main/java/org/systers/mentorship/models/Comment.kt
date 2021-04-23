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
data class Comment(
    @SerializedName("comment")
    val comment: String, // string
    @SerializedName("creation_date")
    val creationDate: Double, // 0
    @SerializedName("id")
    val id: Int, // 0
    @SerializedName("modification_date")
    val modificationDate: Int, // 0
    @SerializedName("relation_id")
    val relationId: Int, // 0
    @SerializedName("task_id")
    val taskId: Int, // 0
    @SerializedName("user_id")
    val userId: Int // 0
): Parcelable