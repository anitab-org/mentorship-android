package org.systers.mentorship.remote.responses

import android.os.Parcel
import android.os.Parcelable

/**
 * This data class represents partial information of user of the system.
 * This is used in APIs not directly related with Users, such as in responses
 * related to MentorshipRelation
 *
 * @param id identifier of the user
 * @param name name of the user
 */
data class RelationUserResponse (val id: Int, val name: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }
    
    /**
     * Class responsible for generating instances of this Parcelable class from a Parcel
     */
    companion object CREATOR : Parcelable.Creator<RelationUserResponse> {
        override fun createFromParcel(parcel: Parcel): RelationUserResponse {
            return RelationUserResponse(parcel)
        }

        override fun newArray(size: Int): Array<RelationUserResponse?> {
            return arrayOfNulls(size)
        }
    }
}
