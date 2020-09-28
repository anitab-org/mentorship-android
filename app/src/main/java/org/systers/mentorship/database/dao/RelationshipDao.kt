package org.systers.mentorship.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import org.systers.mentorship.models.Relationship

@Dao
interface RelationshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update_or_insert(relationship: Relationship): Long

    @Query("SELECT * FROM RELATIONSHIP LIMIT 1")
    fun queryCurrentRelation(): List<Relationship>
}