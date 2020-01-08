package org.systers.mentorship.models

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.systers.mentorship.db.TaskConverter

/**
 * This class represents statistics of the user actions on the app.
 * @param name the name of the user
 * @param pendingRequests number of pending requests
 * @param acceptedRequests number of accepted requests
 * @param completedRelations number of completed relations
 * @param cancelledRelations number of cancelled relations
 * @param rejectedRequests number of rejected requests
 * @param achievements a list of up-to 3 completed tasks
 */

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "home_stats", primaryKeys = ["name"])
@TypeConverters(TaskConverter::class)
data class HomeStatistics(
        val name: String,
        @SerializedName("pending_requests")
        val pendingRequests: Int,
        @SerializedName("accepted_requests")
        val acceptedRequests: Int,
        @SerializedName("completed_relations")
        val completedRelations: Int,
        @SerializedName("cancelled_relations")
        val cancelledRelations: Int,
        @SerializedName("rejected_requests")
        val rejectedRequests: Int,
        val achievements: List<Task>): Parcelable

