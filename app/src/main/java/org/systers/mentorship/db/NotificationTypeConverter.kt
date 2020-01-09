package org.systers.mentorship.db

import androidx.room.TypeConverter

object NotificationTypeConverter {
    @TypeConverter
    @JvmStatic
    fun notificationTypeToInt(notificationType: Notification.NotificationType): Int? {
        return notificationType.id
    }

    @TypeConverter
    @JvmStatic
    fun intToNotificationType(code: Int?): Notification.NotificationType? {
        return Notification.NotificationType.fromNotificationTypeCode(code.toString())
    }
}
