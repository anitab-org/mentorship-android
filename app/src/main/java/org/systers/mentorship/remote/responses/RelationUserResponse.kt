package org.systers.mentorship.remote.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
                                 val name: String) : Parcelable