package org.systers.mentorship.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents partial information of user of the system.
 * This is used in APIs not directly related with Users, such as in responses
 * related to MentorshipRelation
 *
 * @param id identifier of the mentorship relation
 * @param actionUserId id of the user that sent the request for this mentorship relation
 * @param sentByMe indication if the current user was the action user
 * @param mentor user with mentor role in the relation
 * @param mentee user with mentee role in the relation
 * @param createdAtTimestamp date of creation unix timestamp
 * @param acceptedAtTimestamp date of acceptance unix timestamp
 * @param startAtTimestamp start date unix timestamp
 * @param endAtTimestamp end date unix timestamp
 * @param state state of the relation (@link to MentorshipRelationState)
 * @param notes notes related to the mentorship relation
 */
@Parcelize
data class MentorshipRelationResponse(
        val id: Int,
        @SerializedName("action_user_id") val actionUserId: Int,
        @SerializedName("sent_by_me") val sentByMe: Boolean,
        val mentor: RelationUserResponse,
        val mentee: RelationUserResponse,
        @SerializedName("creation_date")  val createdAtTimestamp: Float,
        @SerializedName("accept_date")  val acceptedAtTimestamp: Float,
        @SerializedName("start_date")  val startAtTimestamp: Float,
        @SerializedName("end_date")  val endAtTimestamp: Float,
        val state: Int,
        val notes: String
): Parcelable
