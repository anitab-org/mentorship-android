package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.vo.UserVO

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeStats(stats: HomeStatistics)

    @Query("SELECT * from home_stats LIMIT 1")
    fun getHomeStats(): LiveData<HomeStatistics>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserVO>)

    @Query("SELECT * FROM users")
    fun findAll(): LiveData<List<UserVO>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun findById(id: Int): LiveData<UserVO>

    @Query("SELECT * FROM users WHERE username = :username")
    fun findByUsername(username: String): LiveData<UserVO>
}
