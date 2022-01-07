package org.anitab.mentorship.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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
 * @param needMentoring true, if user wants to be mentored, false if otherwise
 * @param availableToMentor true, if user is available to mentor, false if otherwise
 * @param slackUsername Slack username
 */

@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey @field:SerializedName("id") var id: Int,
    @field:SerializedName("username") var username: String? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("email") var email: String? = null,
    @field:SerializedName("bio") var bio: String? = null,
    @field:SerializedName("location") var location: String? = null,
    @field:SerializedName("occupation") var occupation: String? = null,
    @field:SerializedName("organization") var organization: String? = null,
    @field:SerializedName("interests") var interests: String? = null,
    @field:SerializedName("skills") var skills: String? = null,
    @field:SerializedName("needMentoring") var needMentoring: Boolean? = null,
    @field:SerializedName("availableToMentor") var availableToMentor: Boolean? = null,
    @field:SerializedName("slackUsername") var slackUsername: String? = null
) : Parcelable
