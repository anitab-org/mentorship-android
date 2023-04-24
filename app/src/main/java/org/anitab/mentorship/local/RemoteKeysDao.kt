package org.anitab.mentorship.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_keys")
    suspend fun getRemoteKeys(): List<RemoteKeysEntity>

    @Query("SELECT * FROM remote_keys WHERE userId = :userId")
    suspend fun remoteKeysRepoId(userId: Int): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
