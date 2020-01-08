package org.systers.mentorship.vo

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents all the information related to a user of the system.
 * It has some values non-nullable, because they serve e.g as Primary Key in Room.
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
@Entity(tableName = "users", primaryKeys = ["username"])
data class UserVO(
    val id: Int,
    val username: String,
    val name: String?,
    val email: String?,
    val bio: String?,
    val location: String?,
    val occupation: String?,
    val organization: String?,
    val interests: String?,
    val skills: String?,
    @SerializedName("need_mentoring") val needsMentoring: Boolean,
    @SerializedName("available_to_mentor") val isAvailableToMentor: Boolean,
    @SerializedName("slack_username") val slackUsername: String?
)
