package org.systers.mentorship.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents the notifications related to a user.
 *
 * @param userId identifier of the user
 * @param header header of the notification stating the type of notification
 * @param description description of the state of notification
 * @param message the message of the notification
 * @param requestDetail details of the request for which the notification was generated
 * @param state state of the notification (@link to NotificationState)
 * */
@SuppressLint("ParcelCreator")
@Parcelize
data class Notification (
        var userId : Int ,
        var header : String ,
        var description : String ,
        var message : String ,
        var requestDetail : Relationship ,
        var state : NotificationState
) : Parcelable
