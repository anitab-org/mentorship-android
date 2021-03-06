package org.systers.mentorship.data

import androidx.lifecycle.LiveData
import androidx.room.*
import org.systers.mentorship.models.User


@Dao
interface userProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun storeUserProfile(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUserProfile(user: User)

    @Query("delete from userProfileTable")
    suspend fun deleteAllUserData()

    @Query("select * from userProfileTable")
    fun getUserProfile(): LiveData<User>
}