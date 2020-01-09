package org.systers.mentorship.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.systers.mentorship.view.activities.FRAGMENT_RELATION
import org.systers.mentorship.view.activities.FRAGMENT_REQUESTS

@Entity(tableName = "notifications")
@TypeConverters(NotificationTypeConverter::class)
/**
 * Class representing push notification from Firebase Cloud Messaging.
 */
data class Notification(
    @PrimaryKey val createdTimestamp: Long,
    val title: String?,
    val message: String?,
    val type: NotificationType
) {
    /**
     * Represents the notification type – e.g click action in the UI depends on it
     */
    enum class NotificationType(val id: Int, val symbolicName: String) {
        UNKNOWN(0, "none"),
        NEW_REQUEST(1, FRAGMENT_REQUESTS),
        REQUEST_ACCEPTED(2, FRAGMENT_RELATION),
        REQUEST_REJECTED(3, FRAGMENT_REQUESTS),
        REQUEST_COMPLETED(4, FRAGMENT_REQUESTS);

        companion object {
            fun fromNotificationTypeCode(code: String?): NotificationType {
                if (code == null) return UNKNOWN

                val codeInt: Int
                try {
                    codeInt = code.toInt()
                } catch (e: NumberFormatException) {
                    return UNKNOWN
                }

                return when (codeInt) {
                    0 -> UNKNOWN
                    1 -> NEW_REQUEST
                    2 -> REQUEST_ACCEPTED
                    3 -> REQUEST_REJECTED
                    4 -> REQUEST_COMPLETED
                    else -> UNKNOWN
                }
            }
        }
    }
}

