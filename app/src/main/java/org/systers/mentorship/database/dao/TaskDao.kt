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
    fun update_or_insert(task: List<Task>)

    @Query("SELECT * FROM TASKS WHERE relationId=:id")
    fun getAllTasksForCurrentRelation(id: Int): List<Task>
}