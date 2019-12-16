package org.systers.mentorship.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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

@Parcelize
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

