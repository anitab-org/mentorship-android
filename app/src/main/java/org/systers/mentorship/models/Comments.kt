package org.systers.mentorship.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comments(
     val id: Int,
     val userID: Int,
     val taskID: Int,
     val relationID: Int,
     val creationDate: Float,
     val modificationDate: Float,
     val comment: String
) : Parcelable