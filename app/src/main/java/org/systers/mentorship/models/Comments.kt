package org.systers.mentorship.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comments(
     val id: Int,
     val user_id: Int,
     val task_id: Int,
     val relation_id: Int,
     val creation_date: Float,
     val modification_date: Float,
     val comment: String
) : Parcelable