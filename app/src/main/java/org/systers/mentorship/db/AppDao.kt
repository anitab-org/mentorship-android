package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.systers.mentorship.models.User

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from UserProfile")
    fun getProfile(): LiveData<MutableList<User>>

}