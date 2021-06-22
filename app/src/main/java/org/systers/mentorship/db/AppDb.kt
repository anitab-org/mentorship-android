package org.systers.mentorship.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.systers.mentorship.db.converters.RelationshipConverter
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants.DB_VERSION

@Database(entities = [Relationship::class], version = DB_VERSION)
@TypeConverters(RelationshipConverter::class)
abstract class AppDb : RoomDatabase() {

    abstract fun getRelationshipDao(): RelationshipDao

    companion object {
        @Volatile
        private var instance: AppDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDB(context).also { instance = it }
        }

        private fun createDB(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDb::class.java, "app_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
