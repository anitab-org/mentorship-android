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
    fun insertRelationship(relationship: Relationship)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRelationships(relationships: List<Relationship>)

    @Query("SELECT * FROM relations")
    fun findAll(): LiveData<List<Relationship>>

    @Query("SELECT * FROM relations WHERE id = :id")
    fun findById(id: Int): LiveData<Relationship>
}
