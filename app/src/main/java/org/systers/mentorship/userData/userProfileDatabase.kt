package org.systers.mentorship.userData
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import org.systers.mentorship.models.User

@Database(entities = [User::class],version = 1 , exportSchema = false)
abstract class userProfileDatabase:RoomDatabase() {
    abstract fun userProfileDao(): userProfileDao
    companion object{
        @Volatile
        private var INSTANCE: userProfileDatabase?=null
        fun getDatabase(context:Context): userProfileDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                        context.applicationContext,
                        userProfileDatabase::class.java,
                        "userProfileDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
