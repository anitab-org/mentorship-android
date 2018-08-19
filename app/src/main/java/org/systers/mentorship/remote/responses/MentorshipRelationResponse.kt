package org.systers.mentorship.remote.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readParcelable(RelationUserResponse::class.java.classLoader),
            parcel.readParcelable(RelationUserResponse::class.java.classLoader),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(actionUserId)
        parcel.writeByte(if (sentByMe) 1 else 0)
        parcel.writeParcelable(mentor, flags)
        parcel.writeParcelable(mentee, flags)
        parcel.writeFloat(createdAtTimestamp)
        parcel.writeFloat(acceptedAtTimestamp)
        parcel.writeFloat(startAtTimestamp)
        parcel.writeFloat(endAtTimestamp)
        parcel.writeInt(state)
        parcel.writeString(notes)
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * Class responsible for generating instances of this Parcelable class from a Parcel
     */
    companion object CREATOR : Parcelable.Creator<MentorshipRelationResponse> {
        override fun createFromParcel(parcel: Parcel): MentorshipRelationResponse {
            return MentorshipRelationResponse(parcel)
        }

        override fun newArray(size: Int): Array<MentorshipRelationResponse?> {
            return arrayOfNulls(size)
        }
    }
}
