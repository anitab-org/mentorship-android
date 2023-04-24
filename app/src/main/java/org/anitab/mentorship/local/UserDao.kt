package org.anitab.mentorship.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.anitab.mentorship.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * from user_table")
    fun getAllUsers(): PagingSource<Int, User>

    @Query("DELETE from user_table")
    suspend fun clearAllUsers()
}
