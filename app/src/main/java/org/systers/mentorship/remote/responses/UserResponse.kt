package org.systers.mentorship.remote.responses

import com.google.gson.annotations.SerializedName

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
 * @param needsMentoring true, if user wants to be mentored, false if otherwise
 * @param isAvailableToMentor true, if user is available to mentor, false if otherwise
 * @param slackUsername Slack username
 */
data class UserResponse(
        var id: Int,
        var username: String,
        var name: String,
        var email: String,
        var bio: String,
        var location: String,
        var occupation: String,
        var organization: String,
        var interests: String,
        var skills: String,
        @SerializedName("need_mentoring") var needsMentoring: Boolean,
        @SerializedName("available_to_mentor") var isAvailableToMentor: Boolean,
        @SerializedName("slack_username") var slackUsername: String)

