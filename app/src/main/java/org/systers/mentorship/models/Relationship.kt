package org.systers.mentorship.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents partial information of user of the system.
 * This is used in APIs not directly related with Users, such as in responses
 * related to MentorshipRelation
 *
 * @param id identifier of the mentorship relation
 * @param action_user_id id of the user that sent the request for this mentorship relation
 * @param sent_by_me indication if the current user was the action user
 * @param mentor user with mentor role in the relation
 * @param mentee user with mentee role in the relation
 * @param creation_date date of creation unix timestamp
 * @param accept_date date of acceptance unix timestamp
 * @param start_date start date unix timestamp
 * @param end_date end date unix timestamp
 * @param state state of the relation (@link to RelationState)
 * @param notes notes related to the mentorship relation
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Relationship(
        val id: Int,
        val action_user_id: Int,
        val sent_by_me: Boolean,
        val mentor: RelationUserResponse,
        val mentee: RelationUserResponse,
        val creation_date: Float,
        val accept_date: Float,
        val start_date: Float,
        val end_date: Float,
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
