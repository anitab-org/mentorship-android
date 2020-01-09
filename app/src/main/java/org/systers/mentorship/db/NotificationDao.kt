package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM notifications")
    fun findAll(): LiveData<List<Notification>>

    @Query("DELETE FROM notifications WHERE createdTimestamp = :timestamp")
    fun deleteNotification(timestamp: Long)
}
