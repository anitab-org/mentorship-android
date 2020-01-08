package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.systers.mentorship.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun findAll(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE username = :username")
    fun findByUsername(username: String): LiveData<User>
}
