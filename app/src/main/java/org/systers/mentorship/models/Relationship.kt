package org.systers.mentorship.models

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
 * @param sentOn date of creation unix timestamp
 * @param acceptedOn date of acceptance unix timestamp
 * @param startsOn start date unix timestamp
 * @param endsOn end date unix timestamp
 * @param state state of the relation (@link to RelationState)
 * @param notes notes related to the mentorship relation
 */
@Parcelize
data class Relationship(
        val id: Int,
        @SerializedName("action_user_id") val actionUserId: Int,
        @SerializedName("sent_by_me") val sentByMe: Boolean,
        val mentor: RelationUserResponse,
        val mentee: RelationUserResponse,
        @SerializedName("creation_date")  val sentOn: Float,
        @SerializedName("accept_date")  val acceptedOn: Float,
        @SerializedName("start_date")  val startsOn: Float,
        @SerializedName("end_date")  val endsOn: Float,
        val state: Int,
        val notes: String): Parcelable {
    /**
     * This data class represents partial information of user of the system.
     * This is used in APIs not directly related with Users, such as in responses
     * related to MentorshipRelation
     *
     * @param id identifier of the user
     * @param name name of the user
     */
    @Parcelize
    data class RelationUserResponse (val id: Int,
                                     val name: String): Parcelable
}
