package org.systers.mentorship.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This class represents statistics of the user actions on the app.
 * @param name the name of the user
 * @param pending_requests number of pending requests
 * @param accepted_requests number of accepted requests
 * @param completed_relations number of completed relations
 * @param cancelled_relations number of cancelled relations
 * @param rejected_requests number of rejected requests
 * @param achievements a list of up-to 3 completed tasks
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class HomeStatistics(
        val name: String,
        val pending_requests: Int,
        val accepted_requests: Int,
        val completed_relations: Int,
        val cancelled_relations: Int,
        val rejected_requests: Int,
        val achievements: List<Task>): Parcelable

