package org.systers.mentorship.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents all the information related to a user of the system
 *
 * @param id identifier of the user
 * @param username username of the user
 * @param name name of the user
 * @param email email of the user
 * @param bio bio of the user
 * @param location location, e.g., country or/and city where the user lives
 * @param occupation current occupation of the user
 * @param organization organization to which the user might belong
 * @param interests interests the user possesses
 * @param skills skills the user possesses
 * @param need_mentoring true, if user wants to be mentored, false if otherwise
 * @param available_to_mentor true, if user is available to mentor, false if otherwise
 * @param slack_username Slack username
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class User(
        var id: Int? = null,
        var username: String? = null,
        var name: String? = null,
        var email: String? = null,
        var bio: String? = null,
        var location: String? = null,
        var occupation: String? = null,
        var organization: String? = null,
        var interests: String? = null,
        var skills: String? = null,
        var need_mentoring: Boolean? = null,
        var available_to_mentor: Boolean? = null,
        var slack_username: String? = null
) : Parcelable
