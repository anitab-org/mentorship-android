package org.systers.mentorship.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.systers.mentorship.models.Notification

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotification(notification: Notification)

    @Query("SELECT * FROM notifications")
    fun getNotifications(): List<Notification>

}