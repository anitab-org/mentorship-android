package org.systers.mentorship.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import org.systers.mentorship.models.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update_or_insert(task: Task): Long

    @Query("SELECT * FROM TASKS")
    fun getAllTasks(): Observable<List<Task>>
}