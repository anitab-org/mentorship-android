package org.systers.mentorship.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class Notification(
        @PrimaryKey val id: String,
        val title: String,
        val message: String,
        val creationTime: Float,
        val type: Int
)

/*
    Notification types:
    0 -> new request
    1 -> request was accepted
    2 -> request was rejected
    3 -> new task
    4 -> new achievement
 */
