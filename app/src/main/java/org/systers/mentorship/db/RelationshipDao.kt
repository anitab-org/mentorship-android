package org.systers.mentorship.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.systers.mentorship.models.Relationship

@Dao
interface RelationshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelationship(relationship: Relationship): Long

    @Query("delete from Relationships where id = :id")
    suspend fun deleteRelatioship(id: Int)

    @Query("select * from Relationships")
    fun getAllRelationships(): LiveData<List<Relationship>>

    @Query("select * from Relationships where pending = 1")
    fun getPendingRelationships(): LiveData<List<Relationship>>
}
