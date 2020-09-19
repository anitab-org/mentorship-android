package org.systers.mentorship.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.Constants.ROOM_DB_VERSION

@Database(entities = [User::class], version = ROOM_DB_VERSION)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDao(): AppDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        // when we instantiate the AppDatabase object, then this will be called
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDB(context).also { instance = it }
        }

        private fun createDB(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db")
                        .fallbackToDestructiveMigration()
                        .build()

    }

}