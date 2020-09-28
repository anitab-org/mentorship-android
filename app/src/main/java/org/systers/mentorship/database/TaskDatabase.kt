package org.systers.mentorship.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.systers.mentorship.database.dao.RelationshipDao
import org.systers.mentorship.database.dao.TaskDao
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task

@Database(entities = arrayOf(Relationship::class, Task::class), version = 4)
@TypeConverters(Converters::class, TaskConverters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract val relationshipDao: RelationshipDao
    abstract val taskDao: TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            TaskDatabase::class.java,
                            "task_database"
                    )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}