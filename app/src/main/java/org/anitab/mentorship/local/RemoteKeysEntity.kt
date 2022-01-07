package org.anitab.mentorship.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "remote_keys")
@Parcelize
data class RemoteKeysEntity(
    @PrimaryKey val userId: Int?,
    val prevKey: Int?,
    val nextKey: Int?
) : Parcelable
