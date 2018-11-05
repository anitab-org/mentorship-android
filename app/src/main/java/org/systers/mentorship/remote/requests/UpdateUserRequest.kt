package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

/**
 * This data class represents all the information that can be updated by a user of the system
 *
 * @param name name of the user
 * @param username username of the user
 * @param bio bio of the user
 * @param location location, e.g., country or/and city where the user lives
 * @param occupation current occupation of the user
 * @param organization organization to which the user might belong
 * @param interests interests the user possesses
 * @param skills skills the user possesses
 * @param needsMentoring true, if user wants to be mentored, false if otherwise
 * @param isAvailableToMentor true, if user is available to mentor, false if otherwise
 * @param slackUsername Slack username
 */
data class UpdateUserRequest(
        var name: String? = null,
        var username: String? = null,
        var bio: String? = null,
        var location: String? = null,
        var occupation: String? = null,
        var organization: String? = null,
        var interests: String? = null,
        var skills: String? = null,
        @SerializedName("need_mentoring") var needsMentoring: Boolean? = null,
        @SerializedName("available_to_mentor") var isAvailableToMentor: Boolean? = null,
        @SerializedName("slack_username") var slackUsername: String? = null)
