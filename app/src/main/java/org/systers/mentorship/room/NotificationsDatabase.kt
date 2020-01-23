package org.systers.mentorship.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.systers.mentorship.models.Notification
import org.systers.mentorship.room.dao.NotificationDAO
import org.systers.mentorship.utils.Constants.NOTIFICATIONS_DB_NAME

@Database(entities = [Notification::class], version = 1)
abstract class NotificationsDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDAO

    companion object {
        @Volatile
        private var instance: NotificationsDatabase? = null

        fun getInstance(context: Context): NotificationsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        NotificationsDatabase::class.java,
                        NOTIFICATIONS_DB_NAME)
                        .allowMainThreadQueries()
                        .build()
    }
}