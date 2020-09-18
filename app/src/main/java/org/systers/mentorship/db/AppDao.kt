package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.User

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from UserProfile")
    fun getProfile(): LiveData<MutableList<User>>

    @Query("select * from Tasks where isDone = 'false'")
    fun getIncompleteTasks(): LiveData<MutableList<Task>>

    @Query("select * from Tasks where isDone = 'true'")
    fun getCompletedTasks(): LiveData<MutableList<Task>>

}