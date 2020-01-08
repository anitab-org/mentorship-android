package org.systers.mentorship.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.vo.UserVO

@Database(entities = [UserVO::class, Relationship::class, HomeStatistics::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun relationshipDao(): RelationshipDao
    
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}
